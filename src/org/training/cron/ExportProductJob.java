/**
 *
 */
package org.training.cron;

import static de.hybris.platform.util.CSVConstants.DEFAULT_LINE_SEPARATOR;
import static java.nio.charset.StandardCharsets.UTF_8;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.impex.jalo.ImpExManager;
import de.hybris.platform.impex.jalo.ImpExMedia;
import de.hybris.platform.impex.jalo.exp.ExportConfiguration;
import de.hybris.platform.impex.jalo.exp.ExportUtils;
import de.hybris.platform.impex.jalo.exp.Exporter;
import de.hybris.platform.impex.jalo.exp.ImpExExportMedia;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.impex.ExportService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.training.model.cron.ExportProductCronJobModel;


/**
 * Exporter job to generate a CSV media that contains products goes offline in X days. X is 5 by default but it can be
 * configured.
 *
 * The generated files can be downloaded in the Multimedia menu in HMC. The prefix of the generated files is
 * products_goes_offline_. Postfix is the time when the export is running.
 *
 * The export job uses an impex script to look for products. This script must be placed on the file system in a specific
 * folder: $HIBRIS_BIN_DIR/custom/training/impex. The file name is productexport.impex but it can be configured.
 */
public class ExportProductJob extends AbstractJobPerformable<ExportProductCronJobModel>
{

	private static final Logger LOG = Logger.getLogger(ExportProductJob.class);

	@Resource
	private ExportService exportService;

	@Resource
	private CatalogVersionService catalogVersionService;


	@Override
	public PerformResult perform(final ExportProductCronJobModel model)
	{

		LOG.info("Export process is starting ...");
		PerformResult result = null;

		try
		{
			final LocalDateTime localDate = LocalDateTime.now();

			final Path impexFile = getImpexFile(model.getImpexFile());
			LOG.info("Impex file: " + impexFile);

			final String script = getImpexScript(impexFile, model.getXDaysToGoOffline());

			ImpExMedia scriptmedia = null;
			ImpExExportMedia exportedDataTarget = null;

			final File scriptFile = generateScriptFile(script);

			scriptmedia = ImpExManager.getInstance().createImpExMedia("impex_" + localDate);
			scriptmedia.setFile(scriptFile);
			exportedDataTarget = ExportUtils.createDataExportTarget("products_goes_offline_" + localDate);

			final ExportConfiguration config = new ExportConfiguration(scriptmedia, ImpExManager.getExportOnlyMode());
			config.setSingleFile(true);
			config.setDataExportTarget(exportedDataTarget);

			final Exporter exporter = new Exporter(config);

			exporter.export();

			result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);

			LOG.info("Product export is done.");
			LOG.info("Generated file: " + exportedDataTarget.getCode());

		}
		catch (final JaloBusinessException | IOException e)
		{
			LOG.error(e);
			result = new PerformResult(CronJobResult.ERROR, CronJobStatus.FINISHED);
		}

		return result;

	}

	private File generateScriptFile(final String script) throws IOException, FileNotFoundException
	{
		final File scriptFile = File.createTempFile("productexportscript", ".temp");
		final FileOutputStream fos = new FileOutputStream(scriptFile);
		fos.write(script.getBytes());
		fos.close();
		return scriptFile;
	}

	private String getImpexScript(final Path impexFile, final int days) throws IOException
	{
		final String script = Files.lines(impexFile, UTF_8).collect(Collectors.joining(DEFAULT_LINE_SEPARATOR));
		return script.replaceAll("@@@DAYS@@@", "" + days);
	}

	private Path getImpexFile(final String fileName)
	{
		final String hybrisBinDir = System.getProperty("HYBRIS_BIN_DIR");
		final Path impexFile = Paths.get(hybrisBinDir, "custom", "training", "impex", fileName);
		return impexFile;
	}

}

