package com.orioninc.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DbRecordTest {
    DbRecord dbRecord = new DbRecord(LocalDateTime.now(), "test", 1, 1);

}
