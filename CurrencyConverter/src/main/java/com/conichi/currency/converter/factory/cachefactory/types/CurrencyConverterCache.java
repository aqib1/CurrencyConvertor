package com.conichi.currency.converter.factory.cachefactory.types;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.factory.cachefactory.caches.CCCaches;
import com.conichi.currency.converter.repository.CurrencyConverterRepository;

public class CurrencyConverterCache implements CCCaches {

	private static CurrencyConverterCache currencyConverterCache;
	private ReadWriteLock reentrantLock;
	private Lock readLock;
	private Lock writeLock;
	@Autowired
	private CurrencyConverterRepository repository;

	private void init() {
		reentrantLock = new ReentrantReadWriteLock();
		readLock = reentrantLock.readLock();
		writeLock = reentrantLock.writeLock();
	}

	@Override
	public CurrencyConverterEntity presist(CurrencyConverterEntity entity) {
		writeLock.lock();
		try {
			return repository.save(entity);
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while presisting data => " + e.getMessage(), e);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public CurrencyConverterEntity read(String query) {
		readLock.lock();
		try {
			return repository.findByQuery(query);
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while reading data => " + e.getMessage(), e);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public CCCaches deleteAll() {
		writeLock.lock();
		try {
			if (repository.count() > 0) {
				repository.deleteAll();
			}
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while deleting data => " + e.getMessage(), e);
		} finally {
			writeLock.unlock();
		}
		return this;
	}

	/**
	 * @author Aqib_Javed
	 * @version 1.0
	 * @since 9/21/2019
	 *        <p>
	 *        double check locking pattern
	 *        </p>
	 */
	private CurrencyConverterCache() {

	}

	public static CurrencyConverterCache getInstance() {
		if (Objects.isNull(currencyConverterCache))
			synchronized (CurrencyConverterCache.class) {
				if (Objects.isNull(currencyConverterCache)) {
					currencyConverterCache = new CurrencyConverterCache();
					currencyConverterCache.init();
				}
			}
		return currencyConverterCache;
	}
}
