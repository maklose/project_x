package de.dhbw.muehle_util.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.dhbw.muehle_api.ESpielsteinFarbe;
import de.dhbw.muehle_api.EPositionIndex;
import de.dhbw.muehle_api.ISpielstein;
import de.dhbw.muehle_api.Position;
import de.dhbw.muehle_api.strategy.ISpielzug;
import de.dhbw.muehle_api.strategy.IStrategie;
import de.dhbw.muehle_api.strategy.IStrategieFactory;
import de.dhbw.muehle_api.strategy.StrategieException;

public class PluginClassLoader extends URLClassLoader {

	  private ClassLoader system;
	  
	  private static Set<String> m_Saveclasse = new HashSet<String>();
	  {
		  m_Saveclasse.add( EPositionIndex.class.getName() );
		  m_Saveclasse.add( ESpielsteinFarbe.class.getName() );		  
		  m_Saveclasse.add( ISpielstein.class.getName() );
		  m_Saveclasse.add( Position.class.getName() );		  
		  
		  m_Saveclasse.add( ISpielzug.class.getName() );
		  m_Saveclasse.add( IStrategie.class.getName() );
		  m_Saveclasse.add( IStrategieFactory.class.getName() );
		  m_Saveclasse.add( StrategieException.class.getName() );
	  }

	  public PluginClassLoader(URL[] classpath, ClassLoader parent) {
	    super(classpath, parent);
	    system = getSystemClassLoader();
	  }

	  @Override
	  protected synchronized Class<?> loadClass(String name, boolean resolve)
	      throws ClassNotFoundException {

	    Class<?> c = findLoadedClass(name);
	    if (c == null) {
	      if (isSystemClass(name)) {
	        c = super.loadClass(name, resolve);
	      } else {
	        try {
	          c = findClass(name);
	        } catch (ClassNotFoundException e) {
	          c = super.loadClass(name, resolve);
	        }
	      }
	    }
	    if (resolve) {
	      resolveClass(c);
	    }
	    return c;
	  }

	  private boolean isSystemClass(String name) {
	    return ( m_Saveclasse.contains( name ) || name.startsWith("java.") || name.startsWith("javax.") ||
	           name.startsWith("com.sun."));
	  }

	  @Override
	  public URL getResource(String name) {
	    URL url = findResource(name);
	    if (url == null) {
	      url = super.getResource(name);
	    }
	    return url;
	  }

	  @Override
	  public Enumeration<URL> getResources(String name) throws IOException {
	    Enumeration<URL> localUrls = findResources(name);
	    Enumeration<URL> parentUrls = null;
	    if (getParent() != null) {
	      parentUrls = getParent().getResources(name);
	    }
	    final List<URL> urls = new ArrayList<URL>();
	    if (localUrls != null) {
	      while (localUrls.hasMoreElements()) {
	        urls.add(localUrls.nextElement());
	      }
	    }
	    if (parentUrls != null) {
	      while (parentUrls.hasMoreElements()) {
	        urls.add(parentUrls.nextElement());
	      }
	    }
	    return new Enumeration<URL>() {
	      Iterator<URL> iter = urls.iterator();

	      public boolean hasMoreElements() {
	        return iter.hasNext();
	      }

	      public URL nextElement() {
	        return iter.next();
	      }
	    };
	  }

	  @Override
	  public InputStream getResourceAsStream(String name) {
	    URL url = getResource(name);
	    try {
	      return url != null ? url.openStream() : null;
	    } catch (IOException e) {
	    }
	    return null;
	  }
}