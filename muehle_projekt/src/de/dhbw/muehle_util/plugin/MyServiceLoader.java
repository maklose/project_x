package de.dhbw.muehle_util.plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class MyServiceLoader {

	public static <T> List<T> load(String p_PluginsDir, Class<T> p_ServiceClass)
	{
		List<T> l_Facs = new ArrayList<T>();
		
		File l_PluginDir = new File(p_PluginsDir);
		
		for( File dir: l_PluginDir.listFiles() )
		{
			if( dir.isDirectory() )
			{
				File[] l_Jars = dir.listFiles( new FilenameFilter() 
				{
					
					@Override
					public boolean accept(File file, String name) {
						// TODO Auto-generated method stub
						return name.endsWith( ".jar" );
					}
				});
				
				List<URL> l_Urls = new ArrayList<URL>();
				for( File jar: l_Jars )
				{
					try {
						l_Urls.add( jar.toURI().toURL() );
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				if( !l_Urls.isEmpty() )
				{
					PluginClassLoader l_ClassLoader = new PluginClassLoader(l_Urls.toArray( new URL[ l_Urls.size() ]), Thread.currentThread().getContextClassLoader() );
					
					ServiceLoader<T> loader = ServiceLoader.load(p_ServiceClass, l_ClassLoader);
					for( T fac : loader )
					{
						l_Facs.add( fac );
					}
				}
			}
		}	
		
		return l_Facs;		
	}
	
}
