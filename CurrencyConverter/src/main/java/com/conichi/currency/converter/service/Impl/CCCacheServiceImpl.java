package com.conichi.currency.converter.service.Impl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.repository.CurrencyConverterRepository;
import com.conichi.currency.converter.service.CCCacheService;

@Service
public class CCCacheServiceImpl implements CCCacheService {

	private ReadWriteLock reentrantLock = new ReentrantReadWriteLock();
	private Lock readLock = reentrantLock.readLock();
	private Lock writeLock = reentrantLock.writeLock();
	@Autowired
	private CurrencyConverterRepository repository;

	@Override
	public CurrencyConverterEntity writeCache(CurrencyConverterEntity entity) {
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
	public void deleteAll() {
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
	}

	@Override
	public long count() {
		readLock.lock();
		try {
			return repository.count();
		} catch (Exception e) {
			throw new CachePresistException("Exeption occured while counting data => " + e.getMessage(), e);
		} finally {
			readLock.unlock();
		}
	}
}
