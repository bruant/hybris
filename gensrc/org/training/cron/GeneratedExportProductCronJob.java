/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 4, 2018 5:52:55 AM                      ---
 * ----------------------------------------------------------------
 */
package org.training.cron;

import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.training.constants.TrainingConstants;

/**
 * Generated class for type {@link org.training.cron.ExportProductCronJob ExportProductCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedExportProductCronJob extends CronJob
{
	/** Qualifier of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute **/
	public static final String XDAYSTOGOOFFLINE = "xDaysToGoOffline";
	/** Qualifier of the <code>ExportProductCronJob.impexFile</code> attribute **/
	public static final String IMPEXFILE = "impexFile";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(XDAYSTOGOOFFLINE, AttributeMode.INITIAL);
		tmp.put(IMPEXFILE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExportProductCronJob.impexFile</code> attribute.
	 * @return the impexFile - Name of the file that contains the impex script
	 */
	public String getImpexFile(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IMPEXFILE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExportProductCronJob.impexFile</code> attribute.
	 * @return the impexFile - Name of the file that contains the impex script
	 */
	public String getImpexFile()
	{
		return getImpexFile( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExportProductCronJob.impexFile</code> attribute. 
	 * @param value the impexFile - Name of the file that contains the impex script
	 */
	public void setImpexFile(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IMPEXFILE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExportProductCronJob.impexFile</code> attribute. 
	 * @param value the impexFile - Name of the file that contains the impex script
	 */
	public void setImpexFile(final String value)
	{
		setImpexFile( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute.
	 * @return the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public Integer getXDaysToGoOffline(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, XDAYSTOGOOFFLINE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute.
	 * @return the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public Integer getXDaysToGoOffline()
	{
		return getXDaysToGoOffline( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute. 
	 * @return the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public int getXDaysToGoOfflineAsPrimitive(final SessionContext ctx)
	{
		Integer value = getXDaysToGoOffline( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute. 
	 * @return the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public int getXDaysToGoOfflineAsPrimitive()
	{
		return getXDaysToGoOfflineAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute. 
	 * @param value the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public void setXDaysToGoOffline(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, XDAYSTOGOOFFLINE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute. 
	 * @param value the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public void setXDaysToGoOffline(final Integer value)
	{
		setXDaysToGoOffline( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute. 
	 * @param value the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public void setXDaysToGoOffline(final SessionContext ctx, final int value)
	{
		setXDaysToGoOffline( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ExportProductCronJob.xDaysToGoOffline</code> attribute. 
	 * @param value the xDaysToGoOffline - All products that younger than this value in days will be exported
	 */
	public void setXDaysToGoOffline(final int value)
	{
		setXDaysToGoOffline( getSession().getSessionContext(), value );
	}
	
}
