package com.amazonaws.services.dynamodb;

import com.amazonaws.services.dynamodb.model.BatchGetItemRequest;
import com.amazonaws.services.dynamodb.model.BatchGetItemResult;
import com.amazonaws.services.dynamodb.model.DeleteItemRequest;
import com.amazonaws.services.dynamodb.model.DeleteItemResult;
import com.amazonaws.services.dynamodb.model.PutItemRequest;
import com.amazonaws.services.dynamodb.model.PutItemResult;
import com.amazonaws.services.dynamodb.model.QueryRequest;
import com.amazonaws.services.dynamodb.model.QueryResult;
import com.amazonaws.services.dynamodb.model.UpdateItemRequest;
import com.amazonaws.services.dynamodb.model.UpdateItemResult;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public interface a {
    BatchGetItemResult a(BatchGetItemRequest batchGetItemRequest);

    DeleteItemResult a(DeleteItemRequest deleteItemRequest);

    PutItemResult a(PutItemRequest putItemRequest);

    QueryResult a(QueryRequest queryRequest);

    UpdateItemResult a(UpdateItemRequest updateItemRequest);
}
