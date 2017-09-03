package com.record.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.record.moudle.entity.LawCase;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LAW_CASE".
*/
public class LawCaseDao extends AbstractDao<LawCase, Long> {

    public static final String TABLENAME = "LAW_CASE";

    /**
     * Properties of entity LawCase.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property BirthDay = new Property(2, String.class, "birthDay", false, "BIRTH_DAY");
        public final static Property Address = new Property(3, String.class, "address", false, "ADDRESS");
        public final static Property Date = new Property(4, String.class, "date", false, "DATE");
        public final static Property CarNo = new Property(5, String.class, "carNo", false, "CAR_NO");
        public final static Property IsPrint = new Property(6, boolean.class, "isPrint", false, "IS_PRINT");
        public final static Property Money = new Property(7, String.class, "money", false, "MONEY");
        public final static Property LawCaseTitle = new Property(8, String.class, "lawCaseTitle", false, "LAW_CASE_TITLE");
        public final static Property DocPath = new Property(9, String.class, "docPath", false, "DOC_PATH");
        public final static Property Doc2Htmlpath = new Property(10, String.class, "doc2Htmlpath", false, "DOC2_HTMLPATH");
    }


    public LawCaseDao(DaoConfig config) {
        super(config);
    }
    
    public LawCaseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LAW_CASE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"BIRTH_DAY\" TEXT," + // 2: birthDay
                "\"ADDRESS\" TEXT," + // 3: address
                "\"DATE\" TEXT," + // 4: date
                "\"CAR_NO\" TEXT," + // 5: carNo
                "\"IS_PRINT\" INTEGER NOT NULL ," + // 6: isPrint
                "\"MONEY\" TEXT," + // 7: money
                "\"LAW_CASE_TITLE\" TEXT," + // 8: lawCaseTitle
                "\"DOC_PATH\" TEXT," + // 9: docPath
                "\"DOC2_HTMLPATH\" TEXT);"); // 10: doc2Htmlpath
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LAW_CASE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LawCase entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String birthDay = entity.getBirthDay();
        if (birthDay != null) {
            stmt.bindString(3, birthDay);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(5, date);
        }
 
        String carNo = entity.getCarNo();
        if (carNo != null) {
            stmt.bindString(6, carNo);
        }
        stmt.bindLong(7, entity.getIsPrint() ? 1L: 0L);
 
        String money = entity.getMoney();
        if (money != null) {
            stmt.bindString(8, money);
        }
 
        String lawCaseTitle = entity.getLawCaseTitle();
        if (lawCaseTitle != null) {
            stmt.bindString(9, lawCaseTitle);
        }
 
        String docPath = entity.getDocPath();
        if (docPath != null) {
            stmt.bindString(10, docPath);
        }
 
        String doc2Htmlpath = entity.getDoc2Htmlpath();
        if (doc2Htmlpath != null) {
            stmt.bindString(11, doc2Htmlpath);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LawCase entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String birthDay = entity.getBirthDay();
        if (birthDay != null) {
            stmt.bindString(3, birthDay);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(4, address);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(5, date);
        }
 
        String carNo = entity.getCarNo();
        if (carNo != null) {
            stmt.bindString(6, carNo);
        }
        stmt.bindLong(7, entity.getIsPrint() ? 1L: 0L);
 
        String money = entity.getMoney();
        if (money != null) {
            stmt.bindString(8, money);
        }
 
        String lawCaseTitle = entity.getLawCaseTitle();
        if (lawCaseTitle != null) {
            stmt.bindString(9, lawCaseTitle);
        }
 
        String docPath = entity.getDocPath();
        if (docPath != null) {
            stmt.bindString(10, docPath);
        }
 
        String doc2Htmlpath = entity.getDoc2Htmlpath();
        if (doc2Htmlpath != null) {
            stmt.bindString(11, doc2Htmlpath);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LawCase readEntity(Cursor cursor, int offset) {
        LawCase entity = new LawCase( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // birthDay
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // address
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // date
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // carNo
            cursor.getShort(offset + 6) != 0, // isPrint
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // money
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // lawCaseTitle
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // docPath
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10) // doc2Htmlpath
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LawCase entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBirthDay(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDate(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCarNo(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setIsPrint(cursor.getShort(offset + 6) != 0);
        entity.setMoney(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLawCaseTitle(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setDocPath(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setDoc2Htmlpath(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LawCase entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LawCase entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LawCase entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
