/*
 * Copyright 2009 Toni Menzel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.swissbox.tinybundles.dp;

import java.io.IOException;
import java.io.InputStream;
import org.ops4j.pax.swissbox.tinybundles.core.BuildableBundle;

/**
 * Humane API for constructing Deployment Packages.
 * Capabilities and final format will comply to
 * OSGi Compendium R4 Version 4.2, Deployment Admin Specification Version 1.1
 *
 * @author Toni Menzel (toni@okidokiteam.com)
 * @since May 23, 2009
 */
public interface TinyDP extends BuildableDP, BundleTinyDP, ResourceTinyDP, HeadersTinyDP
{
    
}
