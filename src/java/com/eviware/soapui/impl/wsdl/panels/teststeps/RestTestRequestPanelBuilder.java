/*
 *  soapUI, copyright (C) 2004-2008 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.panels.teststeps;

import com.eviware.soapui.impl.EmptyPanelBuilder;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequest;
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep;
import com.eviware.soapui.support.components.JPropertiesTable;

import javax.swing.*;

/**
 * PanelBuilder for RestTestRequest
 *
 * @author Ole.Matzura
 */

public class RestTestRequestPanelBuilder extends EmptyPanelBuilder<RestTestRequestStep>
{
   public RestTestRequestPanelBuilder()
   {
   }

   public RestTestRequestDesktopPanel buildDesktopPanel( RestTestRequestStep testStep )
   {
      return new RestTestRequestDesktopPanel( testStep );
   }

   public boolean hasDesktopPanel()
   {
      return true;
   }

   public JPanel buildOverviewPanel( RestTestRequestStep testStep )
   {
      RestTestRequest request = testStep.getTestRequest();
      JPropertiesTable<RestTestRequest> table = new JPropertiesTable<RestTestRequest>( "REST TestRequest Properties" );

      // basic properties
      table.addProperty( "Name", "name", true );
      table.addProperty( "Description", "description", true );
//      table.addProperty( "Message Size", "contentLength", false );
      table.addProperty( "Encoding", "encoding", new String[]{null, "UTF-8", "iso-8859-1"} );

      if( request.getOperation() == null )
      {
         table.addProperty( "Path", "path", true );
      }
      else
      {
         table.addProperty( "Endpoint", "endpoint", request.getInterface().getEndpoints() );
         table.addProperty( "Service", "serviceName" );
         table.addProperty( "Resource", "resourcePath" );

      }

      table.addProperty( "Bind Address", "bindAddress", true );
      table.addProperty( "Follow Redirects", "followRedirects", JPropertiesTable.BOOLEAN_OPTIONS );

      // security / authentication
      table.addProperty( "Username", "username", true );
      table.addProperty( "Password", "password", true );
      table.addProperty( "Domain", "domain", true );

      // post-processing
      table.addProperty( "Pretty Print", "prettyPrint", JPropertiesTable.BOOLEAN_OPTIONS );
      table.addProperty( "Dump File", "dumpFile", true ).setDescription( "Dumps response message to specified file" );
      table.addProperty( "Max Size", "maxSize", true ).setDescription( "The maximum number of bytes to receive" );

      table.setPropertyObject( request );

      return table;
   }

   public boolean hasOverviewPanel()
   {
      return true;
   }
}
