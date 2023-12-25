package com.shortify.api.service;

import com.shortify.api.entity.URIRecordEntity;
import com.shortify.api.helper.CRC32HashingHelper;

import java.time.LocalDateTime;

public class URIRecordService {
    public String createUriResource(String longUri) {
        // TODO: Fix bug in which an URI without the protocol gets the current host appended to it`s start
        // Checking if longUri already exists in the database
        URIRecordEntity existantUriRecord = URIRecordEntity.findByLongUri(longUri);

        if (existantUriRecord != null) {
            existantUriRecord.setLastUpdateTime(LocalDateTime.now());
            existantUriRecord.persist();

            return existantUriRecord.getShortUriHash();
        }

        URIRecordEntity uriRecord = new URIRecordEntity(longUri);
        String uriHash = CRC32HashingHelper.getCRC32Representation(longUri);

        uriRecord.setShortUriHash(uriHash);
        uriRecord.setCreationTime(LocalDateTime.now());
        uriRecord.setLastUpdateTime(LocalDateTime.now());

        uriRecord.persistAndFlush();

        return uriHash;
    }

    public URIRecordEntity findURIRecordByHash(String uriHash) {
        return URIRecordEntity.findByHash(uriHash);
    }

    public String getLongUriByHash(String uriHash) {
        URIRecordEntity uriRecordEntity = URIRecordEntity.findByHash(uriHash);

        return uriRecordEntity == null ? null : uriRecordEntity.getLongUri();
    }
}
