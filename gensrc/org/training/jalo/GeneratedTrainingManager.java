/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Jan 4, 2018 3:54:11 PM                      ---
 * ----------------------------------------------------------------
 */
package org.training.jalo;

import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.security.Principal;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.User;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.training.constants.TrainingConstants;
import org.training.cron.ExportProductCronJob;

/**
 * Generated class for type <code>TrainingManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedTrainingManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("dateOfBirth", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.User", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("metaInfo", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public ExportProductCronJob createExportProductCronJob(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( TrainingConstants.TC.EXPORTPRODUCTCRONJOB );
			return (ExportProductCronJob)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating ExportProductCronJob : "+e.getMessage(), 0 );
		}
	}
	
	public ExportProductCronJob createExportProductCronJob(final Map attributeValues)
	{
		return createExportProductCronJob( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.dateOfBirth</code> attribute.
	 * @return the dateOfBirth - Date of birth - Hybris course
	 */
	public Date getDateOfBirth(final SessionContext ctx, final User item)
	{
		return (Date)item.getProperty( ctx, TrainingConstants.Attributes.User.DATEOFBIRTH);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>User.dateOfBirth</code> attribute.
	 * @return the dateOfBirth - Date of birth - Hybris course
	 */
	public Date getDateOfBirth(final User item)
	{
		return getDateOfBirth( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.dateOfBirth</code> attribute. 
	 * @param value the dateOfBirth - Date of birth - Hybris course
	 */
	public void setDateOfBirth(final SessionContext ctx, final User item, final Date value)
	{
		item.setProperty(ctx, TrainingConstants.Attributes.User.DATEOFBIRTH,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>User.dateOfBirth</code> attribute. 
	 * @param value the dateOfBirth - Date of birth - Hybris course
	 */
	public void setDateOfBirth(final User item, final Date value)
	{
		setDateOfBirth( getSession().getSessionContext(), item, value );
	}
	
	@Override
	public String getName()
	{
		return TrainingConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.metaInfo</code> attribute.
	 * @return the metaInfo
	 */
	public String getMetaInfo(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, TrainingConstants.Attributes.Product.METAINFO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.metaInfo</code> attribute.
	 * @return the metaInfo
	 */
	public String getMetaInfo(final Product item)
	{
		return getMetaInfo( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.metaInfo</code> attribute. 
	 * @param value the metaInfo
	 */
	public void setMetaInfo(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, TrainingConstants.Attributes.Product.METAINFO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.metaInfo</code> attribute. 
	 * @param value the metaInfo
	 */
	public void setMetaInfo(final Product item, final String value)
	{
		setMetaInfo( getSession().getSessionContext(), item, value );
	}
	
}
