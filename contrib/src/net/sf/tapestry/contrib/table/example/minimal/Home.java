//
// Tapestry Web Application Framework
// Copyright (c) 2000-2002 by Howard Lewis Ship
//
// Howard Lewis Ship
// http://sf.net/projects/tapestry
// mailto:hship@users.sf.net
//
// This library is free software.
//
// You may redistribute it and/or modify it under the terms of the GNU
// Lesser General Public License as published by the Free Software Foundation.
//
// Version 2.1 of the license should be included with this distribution in
// the file LICENSE, as well as License.html. If the license is not
// included with this distribution, you may find a copy at the FSF web
// site at 'www.gnu.org' or 'www.fsf.org', or you may write to the
// Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139 USA.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied waranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//

package net.sf.tapestry.contrib.table.example.minimal;

import java.util.Locale;

import net.sf.tapestry.contrib.table.model.ITableColumn;
import net.sf.tapestry.contrib.table.model.ITableModel;
import net.sf.tapestry.contrib.table.model.simple.SimpleTableColumn;
import net.sf.tapestry.contrib.table.model.simple.SimpleTableModel;
import net.sf.tapestry.html.BasePage;

/**
 * @author mindbridge
 * @version $Id$
 *
 */
public class Home extends BasePage
{

	public ITableModel getTableModel()
	{
		// Generate the list of data
		Locale[] arrLocales = Locale.getAvailableLocales();

		// Generate the list of columns
		ITableColumn[] arrColumns =
			new ITableColumn[] { new SimpleTableColumn("Locale", true)
			{ public Object getColumnValue(Object objRow)
				{ return ((Locale) objRow).toString();
				}
			}, new SimpleTableColumn("Language", true)
			{
				public Object getColumnValue(Object objRow)
				{
					return ((Locale) objRow).getLanguage();
				}
			}, new SimpleTableColumn("Country", true)
			{
				public Object getColumnValue(Object objRow)
				{
					return ((Locale) objRow).getCountry();
				}
			}, new SimpleTableColumn("Variant", true)
			{
				public Object getColumnValue(Object objRow)
				{
					return ((Locale) objRow).getVariant();
				}
			}, new SimpleTableColumn("ISO Language", true)
			{
				public Object getColumnValue(Object objRow)
				{
					return ((Locale) objRow).getISO3Language();
				}
			}, new SimpleTableColumn("ISO Country", true)
			{
				public Object getColumnValue(Object objRow)
				{
					return ((Locale) objRow).getISO3Country();
				}
			}, };

		// Create the model and return it
		return new SimpleTableModel(arrLocales, arrColumns);
	}
}
