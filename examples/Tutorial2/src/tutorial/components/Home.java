//  Copyright 2004 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package tutorial.components;

import net.sf.tapestry.html.BasePage;

/**
 * Example code for the "creating components" section of the tutorial
 * @author neil clayton
 */
public class Home extends BasePage {
	public Object[] getArraySource() {
		return new Object[] {
			new Object[] { "This is", "a test", "of the array viewer" },
			new Object[] { "There should be nothing in the next two columns", null, null },
			new Object[] { new Integer(1234), Boolean.TRUE, this }
		};
	}
}
