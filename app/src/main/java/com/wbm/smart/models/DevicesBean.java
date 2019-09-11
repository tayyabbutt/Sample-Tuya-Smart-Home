package com.wbm.smart.models;

import com.tuya.smart.android.device.bean.SchemaBean;
import com.tuya.smart.android.hardware.bean.HgwBean;
import com.tuya.smart.sdk.bean.ProductBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DevicesBean implements Serializable {
    public static final String UI_TYPE_RN = "RN";
    private static final String TAG = "DeviceBean";
    public String iconUrl;
    public String devId;
    private Boolean isOnline = false;
    public String name;
    public String schema;
    public String ui;
    public String verSw;
    public String uiType;
    public String productId;
    public String appRnVersion;
    public String uiPhase;
    public boolean rnFind;
    public boolean supportGroup;
    public long time;
    public String pv;
    public String bv;
    public Map<String, SchemaBean> schemaMap;
    public Map<String, Object> dps;
    public Map<String, Object> uiConfig;
    public Map<String, Object> panelConfig;
    public Boolean isShare;
    public String runtimeEnv;
    public String gwType;
    public boolean virtual;
    public String lon;
    public String lat;
    public int ability;
    public String displayMsgs;
    public String displayDps;
    public String quickOpDps;
    private Boolean isLocalOnline;
    private long attribute;
    private int switchDp;
    private List<Integer> faultDps;
    private String schemaExt;
    public long i18nTime;
    private String uiName;
    public String localKey;
    public String uuid;
    private HgwBean hgwBean;
    private ProductBean productBean;
    private String category;
    private String timezoneId;
    private String cadv;
    private String meshId;
    private String nodeId;
    private int topoType = -1;
    private int displayOrder;
    private int homeDisplayOrder;
    private long dpMaxTime;
    private long errorCode;
    private Map<String, Object> skills;
    private Map<String, String> dpName;
    private String devKey;
    private String mac;
    private String parentId;
    private long devAttribute;


    public static String getUiTypeRn() {
        return UI_TYPE_RN;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getVerSw() {
        return verSw;
    }

    public void setVerSw(String verSw) {
        this.verSw = verSw;
    }

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAppRnVersion() {
        return appRnVersion;
    }

    public void setAppRnVersion(String appRnVersion) {
        this.appRnVersion = appRnVersion;
    }

    public String getUiPhase() {
        return uiPhase;
    }

    public void setUiPhase(String uiPhase) {
        this.uiPhase = uiPhase;
    }

    public boolean isRnFind() {
        return rnFind;
    }

    public void setRnFind(boolean rnFind) {
        this.rnFind = rnFind;
    }

    public boolean isSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(boolean supportGroup) {
        this.supportGroup = supportGroup;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getBv() {
        return bv;
    }

    public void setBv(String bv) {
        this.bv = bv;
    }

    public Map<String, SchemaBean> getSchemaMap() {
        return schemaMap;
    }

    public void setSchemaMap(Map<String, SchemaBean> schemaMap) {
        this.schemaMap = schemaMap;
    }

    public Map<String, Object> getDps() {
        return dps;
    }

    public void setDps(Map<String, Object> dps) {
        this.dps = dps;
    }

    public Map<String, Object> getUiConfig() {
        return uiConfig;
    }

    public void setUiConfig(Map<String, Object> uiConfig) {
        this.uiConfig = uiConfig;
    }

    public Map<String, Object> getPanelConfig() {
        return panelConfig;
    }

    public void setPanelConfig(Map<String, Object> panelConfig) {
        this.panelConfig = panelConfig;
    }

    public Boolean getShare() {
        return isShare;
    }

    public void setShare(Boolean share) {
        isShare = share;
    }

    public String getRuntimeEnv() {
        return runtimeEnv;
    }

    public void setRuntimeEnv(String runtimeEnv) {
        this.runtimeEnv = runtimeEnv;
    }

    public String getGwType() {
        return gwType;
    }

    public void setGwType(String gwType) {
        this.gwType = gwType;
    }

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public String getDisplayMsgs() {
        return displayMsgs;
    }

    public void setDisplayMsgs(String displayMsgs) {
        this.displayMsgs = displayMsgs;
    }

    public String getDisplayDps() {
        return displayDps;
    }

    public void setDisplayDps(String displayDps) {
        this.displayDps = displayDps;
    }

    public String getQuickOpDps() {
        return quickOpDps;
    }

    public void setQuickOpDps(String quickOpDps) {
        this.quickOpDps = quickOpDps;
    }

    public Boolean getLocalOnline() {
        return isLocalOnline;
    }

    public void setLocalOnline(Boolean localOnline) {
        isLocalOnline = localOnline;
    }

    public long getAttribute() {
        return attribute;
    }

    public void setAttribute(long attribute) {
        this.attribute = attribute;
    }

    public int getSwitchDp() {
        return switchDp;
    }

    public void setSwitchDp(int switchDp) {
        this.switchDp = switchDp;
    }

    public List<Integer> getFaultDps() {
        return faultDps;
    }

    public void setFaultDps(List<Integer> faultDps) {
        this.faultDps = faultDps;
    }

    public String getSchemaExt() {
        return schemaExt;
    }

    public void setSchemaExt(String schemaExt) {
        this.schemaExt = schemaExt;
    }

    public long getI18nTime() {
        return i18nTime;
    }

    public void setI18nTime(long i18nTime) {
        this.i18nTime = i18nTime;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    public String getLocalKey() {
        return localKey;
    }

    public void setLocalKey(String localKey) {
        this.localKey = localKey;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public HgwBean getHgwBean() {
        return hgwBean;
    }

    public void setHgwBean(HgwBean hgwBean) {
        this.hgwBean = hgwBean;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getCadv() {
        return cadv;
    }

    public void setCadv(String cadv) {
        this.cadv = cadv;
    }

    public String getMeshId() {
        return meshId;
    }

    public void setMeshId(String meshId) {
        this.meshId = meshId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public int getTopoType() {
        return topoType;
    }

    public void setTopoType(int topoType) {
        this.topoType = topoType;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getHomeDisplayOrder() {
        return homeDisplayOrder;
    }

    public void setHomeDisplayOrder(int homeDisplayOrder) {
        this.homeDisplayOrder = homeDisplayOrder;
    }

    public long getDpMaxTime() {
        return dpMaxTime;
    }

    public void setDpMaxTime(long dpMaxTime) {
        this.dpMaxTime = dpMaxTime;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Object> skills) {
        this.skills = skills;
    }

    public Map<String, String> getDpName() {
        return dpName;
    }

    public void setDpName(Map<String, String> dpName) {
        this.dpName = dpName;
    }

    public String getDevKey() {
        return devKey;
    }

    public void setDevKey(String devKey) {
        this.devKey = devKey;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public long getDevAttribute() {
        return devAttribute;
    }

    public void setDevAttribute(long devAttribute) {
        this.devAttribute = devAttribute;
    }
}
