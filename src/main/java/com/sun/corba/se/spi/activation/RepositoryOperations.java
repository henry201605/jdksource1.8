/***** Lobxxx Translate Finished ******/
package com.sun.corba.se.spi.activation;


/**
* com/sun/corba/se/spi/activation/RepositoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u45/3627/corba/src/share/classes/com/sun/corba/se/spi/activation/activation.idl
* Thursday, April 30, 2015 12:42:08 PM PDT
*/

public interface RepositoryOperations 
{

  // always uninstalled.
  int registerServer (com.sun.corba.se.spi.activation.RepositoryPackage.ServerDef serverDef) throws com.sun.corba.se.spi.activation.ServerAlreadyRegistered, com.sun.corba.se.spi.activation.BadServerDefinition;

  // unregister server definition
  void unregisterServer (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered;

  // get server definition
  com.sun.corba.se.spi.activation.RepositoryPackage.ServerDef getServer (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered;

  // Return whether the server has been installed
  boolean isInstalled (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered;

  // if the server is currently marked as installed.
  void install (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered, com.sun.corba.se.spi.activation.ServerAlreadyInstalled;

  // if the server is currently marked as uninstalled.
  void uninstall (int serverId) throws com.sun.corba.se.spi.activation.ServerNotRegistered, com.sun.corba.se.spi.activation.ServerAlreadyUninstalled;

  // list registered servers
  int[] listRegisteredServers ();

  // servers.
  String[] getApplicationNames ();

  // Find the ServerID associated with the given application name.
  int getServerID (String applicationName) throws com.sun.corba.se.spi.activation.ServerNotRegistered;
} // interface RepositoryOperations
