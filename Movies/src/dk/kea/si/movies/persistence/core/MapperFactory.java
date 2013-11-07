package dk.kea.si.movies.persistence.core;

import java.util.HashMap;


import dk.kea.si.movies.persistence.mappers.AbstractMapper;
import dk.kea.si.movies.util.ApplicationException;

public class MapperFactory {

	/**
	 * A map with all mappers. The key is the domain class name, the
	 * value is the mapper corresponding to the domain class in the key.
	 */
	private HashMap<String, AbstractMapper> mappers;
	
	private static MapperFactory instance;

	/**
	 * The default constructor creates an empty map of mappers.
	 */
	private MapperFactory() {
		mappers = new HashMap<String, AbstractMapper>();
	}
	
	public static synchronized MapperFactory getInstance() {
		if(instance == null) {
			instance = new MapperFactory();
		}
		return instance;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * Retrieves a mapper corresponding to the domain class provided as the parameter.
	 * 
	 * @param domainClass		the domain class for which the mapper is needed.
	 * @return					instance of the mapper corresponding to the specified domain class.
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public AbstractMapper getMapper(Class<? extends Object> domainClass) {
		String domainClassName = parseClassName(domainClass);
		if (!mappers.containsKey(domainClassName)) {
			mappers.put(domainClassName, createMapper(domainClassName));
		}
		AbstractMapper mapper = mappers.get(domainClassName);
		return mapper;
	}

	/**
	 * Retrieves a mapper corresponding to the domain object provided as the parameter.
	 * 
	 * @param obj			the domain object for which the mapper is needed.
	 * @return				instance of the mapper corresponding to the specified domain object.
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	AbstractMapper getMapper(Object obj) {
		Class<?> domainClass = obj.getClass();
		return getMapper(domainClass);
	}

	/**
	 * Extracts the class name from the fully qualified name.
	 * For example, extracts "String" from "java.lang.String".
	 * 
	 * @param c		class which name should be extracted.
	 * @return		the name of the class as a string.
	 */
	private String parseClassName(Class<?> c) {
		String className = c.getName();
		int firstChar = className.lastIndexOf('.') + 1;
		if (firstChar > 0) {
			className = className.substring(firstChar);
		}
		return className;
	}

	/**
	 * Creates a mapper corresponding to the domain class name provided as the parameter.
	 * 
	 * @param domainClassName		the name of the domain class for which the mapper is needed.
	 * @return						instance of the mapper corresponding to the specified domain class name.
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private AbstractMapper createMapper(String domainClassName) {
		AbstractMapper mapperObject = null;
		String mapperClassName = "dk.kea.si.movies.persistence.mappers."
				+ domainClassName + "Mapper";
		try {
			Class<?> mapperClass = Class.forName(mapperClassName);
			mapperObject = (AbstractMapper) mapperClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new ApplicationException(e);
		} catch (InstantiationException e) {
			throw new ApplicationException(e);
		} catch (IllegalAccessException e) {
			throw new ApplicationException(e);
		}
		return mapperObject;
	}
}
