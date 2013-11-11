package dk.kea.si.movies.persistence.core;

import java.util.ArrayList;

import dk.kea.si.movies.domain.Cache;
import dk.kea.si.movies.domain.DomainObject;
import dk.kea.si.movies.persistence.mappers.AbstractMapper;
import dk.kea.si.movies.persistence.mappers.CacheMapper;

//import com.marv.business.entities.AuctionItem;
//import com.marv.business.entities.DomainObject;
//import com.marv.business.entities.User;
//import com.marv.persistence.mappers.AuctionItemMapper;
//import com.marv.persistence.mappers.UserMapper;

public class PersistenceFacade implements Cloneable {

	/**
	 * An instance of PersistenceFacade object.
	 */
	private static PersistenceFacade instance;
	
	private static MapperFactory mapperFactory;
	
	private PersistenceFacade() {
		// private constructor prevents instantiation by untrusted callers
		mapperFactory = MapperFactory.getInstance();
	}
	
	/**
	 * Creates and returns PersistenceFacade instance.
	 * 
	 * @return instance of PersistenceFacade.
	 */
	public static synchronized PersistenceFacade getInstance() {
		if(instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public Object find(long id, Class<?> domainClassName) {
		return mapperFactory.getMapper(domainClassName).find(id);
	}
	
	public ArrayList<? extends DomainObject> findAll(Class<?> domainClassName) {
		return mapperFactory.getMapper(domainClassName).findAll();
	}
	
	/**
	 * Inserts the domain object provided in the parameter into the database.
	 * 
	 * @param domainObject	domain object to be saved.
	 * @return				the id of the last inserted row.
	 */
	public long insert(DomainObject domainObject) {
		return mapperFactory.getMapper(domainObject).insert(domainObject);
	}
	
	/**
	 * Updates the database record corresponding to the domain object provided in the parameter.
	 * 
	 * @param domainObject	domain object to be saved.
	 * @return				quantity of rows updated.
	 */
	public int update(DomainObject domainObject) {
		return mapperFactory.getMapper(domainObject).update(domainObject);
	}
	
	/**
	 * Deletes the domain object provided in the parameter from the database.
	 * 
	 * @param domainObject	domain object to be deleted.
	 * @return				quantity of rows deleted.
	 */
	public int delete(DomainObject domainObject) {
		return mapperFactory.getMapper(domainObject).delete(domainObject);
	}
	
	public boolean save(DomainObject obj) {
		if(obj.isNew()) {
			return insert(obj) > 0;
		} else {
			return update(obj) > 0;
		}
	}

	public Cache findCache(String request) {
		CacheMapper mapper = (CacheMapper) mapperFactory.getMapper(Cache.class);
		return mapper.find(request);
	}

//	public ArrayList<AuctionItem> findAllAuctionItemsByCategory(long categoryId) {
//		AuctionItemMapper mapper = 
//				(AuctionItemMapper)mapperFactory.getMapper(AuctionItem.class);
//		return mapper.findAllByCategory(categoryId);
//	}

//	public User findUserByOpenId(String identifier) {
//		UserMapper mapper = (UserMapper) mapperFactory.getMapper(User.class);
//		return mapper.findByOpenId(identifier);
//	}
}
