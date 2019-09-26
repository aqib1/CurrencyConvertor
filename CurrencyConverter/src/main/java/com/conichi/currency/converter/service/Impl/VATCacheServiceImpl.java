package com.conichi.currency.converter.service.Impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conichi.currency.converter.entities.VATValidatorEntity;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.repository.VATValidatorRepository;
import com.conichi.currency.converter.service.VATCacheService;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Service
public class VATCacheServiceImpl implements VATCacheService {
	/**
	 * 
	 */
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VATCacheServiceImpl.class.getName());
	private ReadWriteLock reentrantLock = new ReentrantReadWriteLock();
	private Lock readLock = reentrantLock.readLock();
	private Lock writeLock = reentrantLock.writeLock();
	@Autowired
	private VATValidatorRepository repository;

	@Override
	public VATValidatorEntity writeCache(VATValidatorEntity entity) {
		writeLock.lock();
		try {
			logger.debug("saving entity["+entity+"] in VATValidatorRepository");
			return repository.save(entity);
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while presisting data => " + e.getMessage(), e);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public VATValidatorEntity read(String query) {
		readLock.lock();
		try {
			logger.debug("read against query["+query+"] from VATValidatorRepository");
			return repository.findByQuery(query);
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while reading data => " + e.getMessage(), e);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public void deleteAll() {
		writeLock.lock();
		try {
			if (repository.count() > 0) {
				logger.debug("delete all from VATValidatorRepository");
				repository.deleteAll();
			}
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while deleting data => " + e.getMessage(), e);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public long count() {
		readLock.lock();
		try {
			logger.debug("count all from VATValidatorRepository");
			return repository.count();
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while counting data => " + e.getMessage(), e);
		} finally {
			readLock.unlock();
		}
	}

}
