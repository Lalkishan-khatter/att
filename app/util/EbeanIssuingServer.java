package util;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.ExpressionFactory;
import io.ebean.cache.ServerCacheManager;

import java.util.Collection;
import java.util.Iterator;

public class EbeanIssuingServer {
    private static EbeanServer ebeanServer = Ebean.getServer("default");

    public static ExpressionFactory expression() {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        return ebeanServer.getExpressionFactory();
    }

    public static void save(Object bean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.save(bean);
    }

    public static void beginTransaction() {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.beginTransaction();
    }

    public static void commitTransaction() {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.commitTransaction();
    }

    public static void rollbackTransaction() {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.rollbackTransaction();
    }

    public static void endTransaction() {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.endTransaction();
    }

    public static void saveAll(Collection collBean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        if (collBean != null && collBean.size() > 0)
            ebeanServer.saveAll(collBean);
    }

    public static void save(Iterator<Object> beanItr) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.save(beanItr);
    }

    public static void update(Object bean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.update(bean);
    }

    public static void updateAll(Collection bean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        if (bean != null && !bean.isEmpty())
            ebeanServer.updateAll(bean);
    }

    public static void delete(Object bean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.delete(bean);
    }

    public static void deleteAll(Collection collBean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.deleteAll(collBean);
    }

    public static void refresh(Object bean) {
        EbeanServer issuingDB = Ebean.getServer("issuing");
        ebeanServer.refresh(bean);
    }


    public static EbeanServer getServer(String serverName) {
        return Ebean.getServer(serverName);
    }

    public static void clearEbeanCache(Boolean inserts, Boolean updates, Boolean delete, String tableName) {
        Ebean.getServer("default").externalModification(tableName, inserts, updates, delete);
        ServerCacheManager serverCacheManager =
                Ebean.getServer("default").getServerCacheManager();
        serverCacheManager.clearAll();
    }
}
