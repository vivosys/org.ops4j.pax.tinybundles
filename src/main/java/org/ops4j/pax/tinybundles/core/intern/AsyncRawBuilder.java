/*
 * Copyright 2011 Toni Menzel.
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
package org.ops4j.pax.tinybundles.core.intern;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.util.Map;
import java.util.jar.JarOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Async Builder that uses PipedOutputStream. Bundle is not built until it gets flushed.
 */
public class AsyncRawBuilder extends RawBuilder {

    private static Logger LOG = LoggerFactory.getLogger( RawBuilder.class );

    public InputStream build( final Map<String, URL> resources,
                             final Map<String, String> headers )
    {
        LOG.debug( "make()" );
        final PipedInputStream pin = new PipedInputStream();
        try {
            final PipedOutputStream pout = new PipedOutputStream( pin );
            final JarOutputStream jarOut = new JarOutputStream( pout );

            new Thread() {
                @Override
                public void run()
                {
                    try {
                        build( resources, headers, jarOut );
                    } catch( IOException e ) {
                       // Someone may close this one before stuff has been flushed.
                    } finally {
                        try {
                            jarOut.close();
                        }catch(IOException e) {
                         //   LOG.warn( "Close ?",e );
                        }
                        LOG.trace( "Copy thread finished." );
                    }
                }
            }.start();
        } catch( IOException e ) {
            LOG.error( "Problem..", e );
        }

        return ( pin );
    }

}
