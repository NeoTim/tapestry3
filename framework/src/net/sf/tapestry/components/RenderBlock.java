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

package net.sf.tapestry.components;

import net.sf.tapestry.AbstractComponent;
import net.sf.tapestry.IComponent;
import net.sf.tapestry.IBinding;
import net.sf.tapestry.IMarkupWriter;
import net.sf.tapestry.IRequestCycle;
import net.sf.tapestry.RequestCycleException;

/**
 *  Renders the text and components wrapped by a {@link Block} component.
 *
 *  [<a href="../../../../../ComponentReference/RenderBlock.html">Component Reference</a>]
 *
 *  <p>It is possible for an RenderBlock to obtain a Block
 *  from a page <em>other than</em> the render page.  This works, even when
 *  the Block contains links, forms and form components.  The action and
 *  direct services will create URLs that properly address this situation.
 *
 *  <p>However, because the rendering page can't know
 *  ahead of time about these foriegn Blocks,
 *  {@link net.sf.tapestry.event.PageRenderListener} methods
 *  (for components and objects of the foriegn page)
 *  via RenderBlock will <em>not</em> be executed.  This specifically
 *  affects the methods of the {@link net.sf.tapestry.event.PageRenderListener} 
 *  interface.
 * 
 *  <p>Before rendering its {@link Block}, RenderBlock will set itself as the 
 *  Block's inserter, and will reset the inserter after the {@link Block} is 
 *  rendered.  This gives the components contained in the {@link Block} access
 *  to its inserted environment via the RenderBlock.  In particular this allows
 *  the contained components to access the informal parameters of the RenderBlock
 *  which effectively allows parameters to be passed to the components contained
 *  in a Block.
 *
 * @author Howard Lewis Ship
 * @version $Id$
 * 
 **/

public class RenderBlock extends AbstractComponent
{
    private Block _block;

    /**
     *  If block is not null,
     *  then the block's inserter is set (to this),
     * {@link net.sf.tapestry.IComponent#renderWrapped(IMarkupWriter, IRequestCycle)}
     *  is invoked on it, and the Block's inserter is set back to its previous state.
     *
     **/

    protected void renderComponent(IMarkupWriter writer, IRequestCycle cycle) throws RequestCycleException
    {
         if (_block != null)
         {
         	// make a copy of the inserter so we don't overwrite completely
         	IComponent previousInserter = _block.getInserter();
         	_block.setInserter(this);
            _block.renderBody(writer, cycle);
            // reset the inserter as it was before we changed it
            _block.setInserter(previousInserter);
         }
    }
    
    public Block getBlock()
    {
        return _block;
    }

    public void setBlock(Block block)
    {
        _block = block;
    }

}