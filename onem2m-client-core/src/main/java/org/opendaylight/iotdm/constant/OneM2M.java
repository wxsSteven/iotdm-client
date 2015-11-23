package org.opendaylight.iotdm.constant;

import org.opendaylight.iotdm.client.exception.Onem2mIncorrectTimeStampError;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by wenxshi on 4/2/15.
 */
public class OneM2M {

    public static final String NAMESPACE = "m2m";
    public static final String PRIMITIVE_PACKAGE = "org.onem2m.xml.protocols";
    public static final String NAMESPACE_DELIMITER = ":";

    public static final String ERROR_INDICATOR="error";

    public static class MIME {
        public static final String VND_ONEM2M_RES_XML = "vnd.onem2m-res+xml";
        public static final String VND_ONEM2M_RES_JSON = "vnd.onem2m-res+json";


        public static final String VND_ONEM2M_NTFY_XML = "vnd.onem2m-ntfy+xml";
        public static final String VND_ONEM2M_NTFY_JSON = "vnd.onem2m-ntfy+json";

        public static final String VND_ONEM2M_PREQ_XML = "vnd.onem2m-PREQ+xml";
        public static final String VND_ONEM2M_PREQ_JSON = "vnd.onem2m-PREQ+json";

        public static final String VND_ONEM2M_PRSP_XML = "vnd.onem2m-PRSP+xml";
        public static final String VND_ONEM2M_PRSP_JSON = "vnd.onem2m-PRSP+json";
    }

    public enum Operation {
        CREATE(BigInteger.valueOf(1)),
        RETRIEVE(BigInteger.valueOf(2)),
        UPDATE(BigInteger.valueOf(3)),
        DELETE(BigInteger.valueOf(4)),
        NOTIFY(BigInteger.valueOf(5));

        private BigInteger operation;

        Operation(BigInteger operation) {
            this.operation = operation;
        }

        public BigInteger value() {
            return operation;
        }

        public static Operation getEnum(BigInteger value) {
            for (Operation o : Operation.values()) {
                if (o.value().equals(value)) {
                    return o;
                }
            }
            return null;
        }
    }


    public enum ConsistencyStrategy {
        ABANDON_MEMBER(BigInteger.valueOf(1)),
        ABANDON_GROUP(BigInteger.valueOf(2)),
        SET_MIXED(BigInteger.valueOf(3));

        private BigInteger value;

        ConsistencyStrategy(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum EncodingType {
        PLAIN(BigInteger.valueOf(0)),
        BASE64_ON_STRING(BigInteger.valueOf(1)),
        BASE64_ON_BINARY(BigInteger.valueOf(2));


        private BigInteger value;

        EncodingType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum EventType {
        DATAOPERATION(BigInteger.valueOf(1)),
        STORAGEBASED(BigInteger.valueOf(2)),
        TIMERBASED(BigInteger.valueOf(3));


        private BigInteger value;

        EventType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum ExecModeType {
        IMMEDIATEONCE(BigInteger.valueOf(1)),
        IMMEDIATEREPEAT(BigInteger.valueOf(2)),
        RANDOMONCE(BigInteger.valueOf(3)),
        RANDOMREPEAT(BigInteger.valueOf(4));


        private BigInteger value;

        ExecModeType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum ExecStatusType {
        INITIATED(BigInteger.valueOf(1)),
        PENDING(BigInteger.valueOf(2)),
        FINISHED(BigInteger.valueOf(3)),
        CANCELLING(BigInteger.valueOf(4)),
        CANCELLED(BigInteger.valueOf(5)),
        STATUS_NON_CANCELLABLE(BigInteger.valueOf(6));


        private BigInteger value;

        ExecStatusType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum LocationSource {
        NETWORK_BASED(BigInteger.valueOf(1)),
        DEVICE_BASED(BigInteger.valueOf(2)),
        SHARING_BASED(BigInteger.valueOf(3));


        private BigInteger value;

        LocationSource(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }


    public enum LogStatus {
        STARTED(BigInteger.valueOf(1)),
        STOPPED(BigInteger.valueOf(2)),
        UNKNOWN(BigInteger.valueOf(3)),
        NOT_PRESENT(BigInteger.valueOf(4)),
        ERROR(BigInteger.valueOf(5));


        private BigInteger value;

        LogStatus(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum LogTypeId {
        SYSTEM(BigInteger.valueOf(1)),
        SECURITY(BigInteger.valueOf(2)),
        EVENT(BigInteger.valueOf(3)),
        TRACE(BigInteger.valueOf(4)),
        PANIC(BigInteger.valueOf(5));


        private BigInteger value;

        LogTypeId(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum MemberType {
        ACCESS_CONTROL_POLICY(BigInteger.valueOf(1)),
        AE(BigInteger.valueOf(2)),
        CONTAINER(BigInteger.valueOf(3)),
        CONTENT_INSTANCE(BigInteger.valueOf(4)),
        CSE_BASE(BigInteger.valueOf(5)),
        DELIVERY(BigInteger.valueOf(6)),
        EVENT_CONFIG(BigInteger.valueOf(7)),
        EXEC_INSTANCE(BigInteger.valueOf(8)),
        GROUP(BigInteger.valueOf(9)),
        LOCATION_POLICY(BigInteger.valueOf(10)),
        M2M_SERVICE_SUBSCRIPTION(BigInteger.valueOf(11)),
        MGMT_CMD(BigInteger.valueOf(12)),
        MGMT_OBJ(BigInteger.valueOf(13)),
        NODE(BigInteger.valueOf(14)),
        POLLING_CHANNEL(BigInteger.valueOf(15)),
        REMOTE_CSE(BigInteger.valueOf(16)),
        REQUEST(BigInteger.valueOf(17)),
        SCHEDULE(BigInteger.valueOf(18)),
        SERVICE_SUBSCRIBED_APP_RULE(BigInteger.valueOf(19)),
        SERVICE_SUBSCRIBED_NODE(BigInteger.valueOf(20)),
        STATS_COLLECT(BigInteger.valueOf(21)),
        STATS_CONFIG(BigInteger.valueOf(22)),
        SUBSCRIPTION(BigInteger.valueOf(23)),
        MIXED(BigInteger.valueOf(24));


        private BigInteger value;

        MemberType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum MgmtDefinition {
        FIRMWARE(BigInteger.valueOf(1001)),
        SOFTWARE(BigInteger.valueOf(1002)),
        MEMORY(BigInteger.valueOf(1003)),
        AREA_NWK_INFO(BigInteger.valueOf(1004)),
        AREA_NWK_DEVICE_INFO(BigInteger.valueOf(1005)),
        BATTERY(BigInteger.valueOf(1006)),
        DEVICE_INFO(BigInteger.valueOf(1007)),
        DEVICE_CAPABILITY(BigInteger.valueOf(1008)),
        REBOOT(BigInteger.valueOf(1009)),
        EVENT_LOG(BigInteger.valueOf(1010)),
        CMDH_POLICY(BigInteger.valueOf(1011)),
        ACTIVE_CMDH_POLICY(BigInteger.valueOf(1012)),
        CMDH_DEFAULTS(BigInteger.valueOf(1013)),
        CMDH_DEF_EC_VALUE(BigInteger.valueOf(1014)),
        CMDH_EC_DEF_PARAM_VALUES(BigInteger.valueOf(1015)),
        CMDH_LIMITS(BigInteger.valueOf(1016)),
        CMDH_NETWORK_ACCESS_RULES(BigInteger.valueOf(1017)),
        CMDH_NW_ACCESS_RULE(BigInteger.valueOf(1018)),
        CMDH_BUFFER(BigInteger.valueOf(1019)),
        UNSPECIFIED(BigInteger.valueOf(0));


        private BigInteger value;

        MgmtDefinition(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum NotificationContentType {
        MODIFIED_ATTRIBUTES(BigInteger.valueOf(1)),
        WHOLE_RESOURCE(BigInteger.valueOf(2)),
        REFERENCE_ONLY(BigInteger.valueOf(3));


        private BigInteger value;

        NotificationContentType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum PendingNotification {
        SEND_LATEST(BigInteger.valueOf(1)),
        SEND_ALL_PENDING(BigInteger.valueOf(2));


        private BigInteger value;

        PendingNotification(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum RequestStatus {
        COMPLETED(BigInteger.valueOf(1)),
        FAILED(BigInteger.valueOf(2)),
        PENDING(BigInteger.valueOf(3)),
        FORWARDED(BigInteger.valueOf(4));


        private BigInteger value;

        RequestStatus(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }

    }

    public enum ResourceStatus {
        CHILD_CREATED(BigInteger.valueOf(1)),
        CHILD_DELETED(BigInteger.valueOf(2)),
        UPDATED(BigInteger.valueOf(3)),
        DELETED(BigInteger.valueOf(4));


        private BigInteger value;

        ResourceStatus(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum SRoleId {
        SOFTWARE_MANAGEMENT("01-001"),
        DEVICE_CONFIGURATION("02-001"),
        DEVICE_DIAGNOSTICS_AND_MANAGEMENT("02-002"),
        DEVICE_FIRMWARE_MANAGEMENT("02-003"),
        DEVICE_TOPOLOGY("02-004"),
        LOCATION("03-001"),
        BASIC_DATA("04-001"),
        ONBOARDING("05-001"),
        SECURITY_ADMINISTRATION("06-001"),
        GROUPS_MANAGEMENT("07-001"),
        EVENT_COLLECTION("08-001");


        private String value;

        SRoleId(String value) {
            this.value = value;
        }


        public String value() {
            return value;
        }
    }

    public enum StatModelType {
        EVENTBASED(BigInteger.valueOf(1));


        private BigInteger value;

        StatModelType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum StatsRuleStatusType {
        ACTIVE(BigInteger.valueOf(1)),
        INACTIVE(BigInteger.valueOf(2));


        private BigInteger value;

        StatsRuleStatusType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum Status {
        SUCCESSFUL(BigInteger.valueOf(1)),
        FAILURE(BigInteger.valueOf(2)),
        IN_PROCESS(BigInteger.valueOf(3));


        private BigInteger value;

        Status(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum StdEventCats {
        DEFAULT(BigInteger.valueOf(1)),
        IMMEDIATE(BigInteger.valueOf(2)),
        BEST_EFFORT(BigInteger.valueOf(3)),
        LATEST(BigInteger.valueOf(4));


        private BigInteger value;

        StdEventCats(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }

        public static StdEventCats getEnum(BigInteger number) {
            for ( StdEventCats  code: StdEventCats.values()) {
                if (code.value.equals(number))
                    return code;
            }
            return null;
        }
    }

    public enum ResultContent {
        NOTHING(BigInteger.valueOf(0)),
        ATTRIBUTES(BigInteger.valueOf(1)),
        HIERARCHICAL_ADDRESS(BigInteger.valueOf(2)),
        HIERARCHICAL_ADDRESS_AND_ATTRIBUTES(BigInteger.valueOf(3)),
        ATTRIBUTES_AND_CHILD_RESOURCES(BigInteger.valueOf(4)),
        ATTRIBUTES_AND_CHILD_RESOURCE_REFERENCES(BigInteger.valueOf(5)),
        CHILD_RESOURCE_REFERENCES(BigInteger.valueOf(6)),
        ORIGINAL_RESOURCE(BigInteger.valueOf(7));


        private BigInteger value;

        ResultContent(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum ResponseType {
        NON_BLOCKING_REQUEST_SYNCH(BigInteger.valueOf(1)),
        NON_BLOCKING_REQUEST_ASYNCH(BigInteger.valueOf(2)),
        BLOCKING_REQUEST(BigInteger.valueOf(3));


        private BigInteger value;

        ResponseType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }


    public enum ExecResultType {
        STATUS_REQUEST_UNSUPPORTED(BigInteger.valueOf(1)),
        STATUS_REQUEST_DENIED(BigInteger.valueOf(2)),
        STATUS_CANCELLATION_DENIED(BigInteger.valueOf(3)),
        STATUS_INTERNAL_ERROR(BigInteger.valueOf(4)),
        STATUS_INVALID_ARGUMENTS(BigInteger.valueOf(5)),
        STATUS_RESOURCES_EXCEEDED(BigInteger.valueOf(6)),
        STATUS_FILE_TRANSFER_FAILED(BigInteger.valueOf(7)),
        STATUS_FILE_TRANSFER_SERVER_AUTHENTICATION_FAILURE(BigInteger.valueOf(8)),
        STATUS_UNSUPPORTED_PROTOCOL(BigInteger.valueOf(9)),
        STATUS_UPLOAD_FAILED(BigInteger.valueOf(10)),
        STATUS_FILE_TRANSFER_FAILED_MULTICAST_GROUP_UNABLE_JOIN(BigInteger.valueOf(11)),
        STATUS_FILE_TRANSFER_FAILED_SERVER_CONTACT_FAILED(BigInteger.valueOf(12)),
        STATUS_FILE_TRANSFER_FAILED_FILE_ACCESS_FAILED(BigInteger.valueOf(13)),
        STATUS_FILE_TRANSFER_FAILED_DOWNLOAD_INCOMPLETE(BigInteger.valueOf(14)),
        STATUS_FILE_TRANSFER_FAILED_FILE_CORRUPTED(BigInteger.valueOf(15)),
        STATUS_FILE_TRANSFER_FILE_AUTHENTICATION_FAILURE(BigInteger.valueOf(16)),
        //todo duplicated with 8,9
//    STATUS_FILE_TRANSFER_FAILED( BigInteger.valueOf(17)),
//    STATUS_FILE_TRANSFER_SERVER_AUTHENTICATION_FAILURE(BigInteger.valueOf(18)),
        STATUS_FILE_TRANSFER_WINDOW_EXCEEDED(BigInteger.valueOf(19)),
        STATUS_INVALID_UUID_FORMAT(BigInteger.valueOf(20)),
        STATUS_UNKNOWN_EXECUTION_ENVIRONMENT(BigInteger.valueOf(21)),
        STATUS_DISABLED_EXECUTION_ENVIRONMENT(BigInteger.valueOf(22)),
        STATUS_EXECUTION_ENVIRONMENT_MISMATCH(BigInteger.valueOf(23)),
        STATUS_DUPLICATE_DEPLOYMENT_UNIT(BigInteger.valueOf(24)),
        STATUS_SYSTEM_RESOURCES_EXCEEDED(BigInteger.valueOf(25)),
        STATUS_UNKNOWN_DEPLOYMENT_UNIT(BigInteger.valueOf(26)),
        STATUS_INVALID_DEPLOYMENT_UNIT_STATE(BigInteger.valueOf(27)),
        STATUS_INVALID_DEPLOYMENT_UNIT_UPDATE_DOWNGRADE_DISALLOWED(BigInteger.valueOf(28)),
        STATUS_INVALID_DEPLOYMENT_UNIT_UPDATE_UPGRADE_DISALLOWED(BigInteger.valueOf(29)),
        STATUS_INVALID_DEPLOYMENT_UNIT_UPDATE_VERSION_EXISTS(BigInteger.valueOf(30));


        private BigInteger value;

        ExecResultType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum DiscResType {
        STRUCTURED(BigInteger.valueOf(1)),
        UNSTRUCTURED(BigInteger.valueOf(2));

        private BigInteger value;

        DiscResType(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum CseTypeId {
        IN_CSE(BigInteger.valueOf(1)),
        MN_CSE(BigInteger.valueOf(2)),
        ASN_CSE(BigInteger.valueOf(3));

        private BigInteger value;

        CseTypeId(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum ResponseStatusCodes {
        ACCEPTED(BigInteger.valueOf(1000)),
        OK(BigInteger.valueOf(2000)),
        CREATED(BigInteger.valueOf(2001)),
        DELETED(BigInteger.valueOf(2002)),
        CHANGED(BigInteger.valueOf(2004)),
        BAD_REQUEST(BigInteger.valueOf(4000)),
        NOT_FOUND(BigInteger.valueOf(4004)),
        OPERATION_NOT_ALLOWED(BigInteger.valueOf(4005)),
        REQUEST_TIMEOUT(BigInteger.valueOf(4008)),
        SUBSCRIPTION_CREATOR_HAS_NO_PRIVILEGE_(BigInteger.valueOf(4101)),
        CONTENTS_UNACCEPTABLE(BigInteger.valueOf(4102)),
        ACCESS_DENIED(BigInteger.valueOf(4103)),
        GROUP_REQUEST_IDENTIFIER_EXISTS(BigInteger.valueOf(4104)),
        CONFLICT(BigInteger.valueOf(4105)),
        INTERNAL_SERVER_ERROR(BigInteger.valueOf(5000)),
        NOT_IMPLEMENTED(BigInteger.valueOf(5001)),
        TARGET_NOT_REACHABLE(BigInteger.valueOf(5103)),
        NO_PRIVILEGE(BigInteger.valueOf(5105)),
        ALREADY_EXISTS(BigInteger.valueOf(5106)),
        TARGET_NOT_SUBSCRIBABLE(BigInteger.valueOf(5203)),
        SUBSCRIPTION_VERIFICATION_INITIATION_FAILED(BigInteger.valueOf(5204)),
        SUBSCRIPTION_HOST_HAS_NO_PRIVILEGE(BigInteger.valueOf(5205)),
        NON_BLOCKING_REQUEST_NOT_SUPPORTED(BigInteger.valueOf(5206)),
        EXTENAL_OBJECT_NOT_REACHABLE(BigInteger.valueOf(6003)),
        EXTENAL_OBJECT_NOT_FOUND(BigInteger.valueOf(6005)),
        MAX_NUMBERF_OF_MEMBER_EXCEEDED(BigInteger.valueOf(6010)),
        MEMBER_TYPE_INCONSISTENT(BigInteger.valueOf(6011)),
        MGMT_SESSION_CANNOT_BE_ESTABLISHED(BigInteger.valueOf(6020)),
        MGMT_SESSION_ESTABLISHMENT_TIMEOUT(BigInteger.valueOf(6021)),
        INVALID_CMDTYPE(BigInteger.valueOf(6022)),
        INVALID_ARGUMENTS(BigInteger.valueOf(6023)),
        INSUFFICIENT_ARGUMENTS(BigInteger.valueOf(6024)),
        MGMT_CONVERSION_ERROR(BigInteger.valueOf(6025)),
        MGMT_CANCELATION_FAILURE(BigInteger.valueOf(6026)),
        ALREADY_COMPLETE(BigInteger.valueOf(6028)),
        COMMAND_NOT_CANCELLABLE(BigInteger.valueOf(6029));


        private BigInteger value;

        ResponseStatusCodes(BigInteger value) {
            this.value = value;
        }

        public static ResponseStatusCodes getEnum(BigInteger number) {
            for (ResponseStatusCodes code : ResponseStatusCodes.values()) {
                if (code.value.equals(number))
                    return code;
            }
            return null;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum AccessControlOperations {
        CREATE(BigInteger.valueOf(1)),
        RETRIEVE(BigInteger.valueOf(2)),
        UPDATE(BigInteger.valueOf(4)),
        DELETE(BigInteger.valueOf(8)),
        DISCOVERY(BigInteger.valueOf(16)),
        NOTIFY(BigInteger.valueOf(32));

        private BigInteger value;

        AccessControlOperations(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum CmdType {
        RESET(BigInteger.valueOf(1)),
        REBOOT(BigInteger.valueOf(2)),
        UPLOAD(BigInteger.valueOf(3)),
        DOWNLOAD(BigInteger.valueOf(4)),
        SOFTWAREINSTALL(BigInteger.valueOf(5)),
        SOFTWAREUNINSTALL(BigInteger.valueOf(6)),
        SOFTWAREUPDATE(BigInteger.valueOf(7));


        private BigInteger value;

        CmdType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum BatteryStatus {
        NORMAL(BigInteger.valueOf(1)),
        CHARGING(BigInteger.valueOf(2)),
        CHARGING_COMPLETE(BigInteger.valueOf(3)),
        DAMAGED(BigInteger.valueOf(4)),
        LOW_BATTERY(BigInteger.valueOf(5)),
        NOT_INSTALLED(BigInteger.valueOf(6)),
        UNKNOWN(BigInteger.valueOf(7));


        private BigInteger value;

        BatteryStatus(BigInteger value) {
            this.value = value;
        }


        public BigInteger value() {
            return value;
        }
    }

    public enum ResourceType {
        ACCESS_CONTROL_POLICY(BigInteger.valueOf(1)),
        AE(BigInteger.valueOf(2)),
        CONTAINER(BigInteger.valueOf(3)),
        CONTENT_INSTANCE(BigInteger.valueOf(4)),
        CSE_BASE(BigInteger.valueOf(5)),
        DELIVERY(BigInteger.valueOf(6)),
        EVENT_CONFIG(BigInteger.valueOf(7)),
        EXEC_INSTANCE(BigInteger.valueOf(8)),
        GROUP(BigInteger.valueOf(9)),
        LOCATION_POLICY(BigInteger.valueOf(10)),
        M2M_SERVICE_SUBSCRIPTION_PROFILE(BigInteger.valueOf(11)),
        MGMT_CMD(BigInteger.valueOf(12)),
        MGMT_OBJ(BigInteger.valueOf(13)),
        NODE(BigInteger.valueOf(14)),
        POLLING_CHANNEL(BigInteger.valueOf(15)),
        REMOTE_CSE(BigInteger.valueOf(16)),
        REQUEST(BigInteger.valueOf(17)),
        SCHEDULE(BigInteger.valueOf(18)),
        SERVICE_SUBSCRIBED_APP_RULE(BigInteger.valueOf(19)),
        SERVICE_SUBSCRIBED_NODE(BigInteger.valueOf(20)),
        STATS_COLLECT(BigInteger.valueOf(21)),
        STATS_CONFIG(BigInteger.valueOf(22)),
        SUBSCRIPTION(BigInteger.valueOf(23)),
        ACCESS_CONTROL_POLICY_ANNC(BigInteger.valueOf(10001)),
        AE_ANNC(BigInteger.valueOf(10002)),
        CONTAINER_ANNC(BigInteger.valueOf(10003)),
        CONTENT_INSTANCE_ANNC(BigInteger.valueOf(10004)),
        GROUP_ANNC(BigInteger.valueOf(10009)),
        LOCATION_POLICY_ANNC(BigInteger.valueOf(10010)),
        MGMT_OBJ_ANNC(BigInteger.valueOf(10013)),
        NODE_ANNC(BigInteger.valueOf(10014)),
        REMOTE_CSE_ANNC(BigInteger.valueOf(10016)),
        SCHEDULE_ANNC(BigInteger.valueOf(10018));


        private BigInteger value;

        ResourceType(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }
    }

    public enum FilterUsage {
        DISCOVERY_CRITERIA(BigInteger.valueOf(1)),
        CONDITONAL_RETRIEVAL(BigInteger.valueOf(2));

        private BigInteger value;

        FilterUsage(BigInteger value) {
            this.value = value;
        }

        public BigInteger value() {
            return value;
        }

    }

    public static class Name {
        public static final String OPERATION = "op";
        public static final String TO = "to";
        public static final String FROM = "fr";
        public static final String REQUEST_IDENTIFIER = "rqi";
        public static final String RESOURCE_TYPE = "ty";
        public static final String NAME = "nm";
        public static final String PRIMITIVE_CONTENT = "pc";
        public static final String ROLE = "rol";
        public static final String ORIGINATING_TIMESTAMP = "ot";
        public static final String REQUEST_EXPIRATION_TIMESTAMP = "rqet";
        public static final String RESULT_EXPIRATION_TIMESTAMP = "rset";
        public static final String OPERATION_EXECUTION_TIME = "oet";
        public static final String RESPONSE_TYPE = "rt";
        public static final String RESULT_PERSISTENCE = "rp";
        public static final String RESULT_CONTENT = "rcn";
        public static final String EVENT_CATEGORY = "ec";
        public static final String DELIVERY_AGGREGATION = "da";
        public static final String GROUP_REQUEST_IDENTIFIER = "gid";
        public static final String FILTER_CRITERIA = "fc";
        public static final String DISCOVERY_RESULT_TYPE = "drt";
        public static final String RESPONSE_STATUS_CODE = "rsc";
        public static final String REQUEST_PRIMITIVE = "rqp";
        public static final String RESPONSE_PRIMITIVE = "rsp";
        public static final String ACCESS_CONTROL_POLICY_IDS = "acpi";
        public static final String ANNOUNCED_ATTRIBUTE = "aa";
        public static final String ANNOUNCE_TO = "at";
        public static final String CREATION_TIME = "ct";
        public static final String EXPIRATION_TIME = "et";
        public static final String LABELS = "lbl";
        public static final String LINK = "lnk";
        public static final String LAST_MODIFIED_TIME = "lt";
        public static final String PARENT_ID = "pi";
        public static final String RESOURCE_ID = "ri";
        public static final String STATE_TAG = "st";
        public static final String RESOURCE_NAME = "rn";
        public static final String PRIVILEGES = "pv";
        public static final String SELF_PRIVILEGES = "pvs";
        public static final String APP_ID = "api";
        public static final String AE_ID = "aei";
        public static final String APP_NAME = "apn";
        public static final String POINT_OF_ACCESS = "poa";
        public static final String ONTOLOGY_REF = "or";
        public static final String NODE_LINK = "nl";
        public static final String CREATOR = "cr";
        public static final String MAX_NR_OF_INSTANCES = "mni";
        public static final String MAX_BYTE_SIZE = "mbs";
        public static final String MAX_INSTANCE_AGE = "mia";
        public static final String CURRENT_NR_OF_INSTANCES = "cni";
        public static final String CURRENT_BYTE_SIZE = "cbs";
        public static final String LOCATION_ID = "li";
        public static final String CONTENT_INFO = "cnf";
        public static final String CONTENT_SIZE = "cs";
        public static final String CONTENT = "con";
        public static final String CSE_TYPE = "cst";
        public static final String CSE_ID = "csi";
        public static final String SUPPORTED_RESOURCE_TYPE = "srt";
        public static final String NOTIFICATION_CONGESTION_POLICY = "ncp";
        public static final String SOURCE = "sr";
        public static final String TARGET = "tg";
        public static final String LIFESPAN = "ls";
        public static final String EVENT_CAT = "eca";
        public static final String DELIVERY_META_DATA = "dmd";
        public static final String AGGREGATED_REQUEST = "arq";
        public static final String EVENT_ID = "evi";
        public static final String EVENT_TYPE = "evt";
        public static final String EVENT_START = "evs";
        public static final String EVENT_END = "eve";
        public static final String OPERATION_TYPE = "opt";
        public static final String DATA_SIZE = "ds";
        public static final String EXEC_STATUS = "exs";
        public static final String EXEC_RESULT = "exr";
        public static final String EXEC_DISABLE = "exd";
        public static final String EXEC_TARGET = "ext";
        public static final String EXEC_MODE = "exm";
        public static final String EXEC_FREQUENCY = "exf";
        public static final String EXEC_DELAY = "exy";
        public static final String EXEC_NUMBER = "exn";
        public static final String EXEC_REQ_ARGS = "exra";
        public static final String EXEC_ENABLE = "exe";
        public static final String MEMBER_TYPE = "mt";
        public static final String CURRENT_NR_OF_MEMBERS = "cnm";
        public static final String MAX_NR_OF_MEMBERS = "mnm";
        public static final String MEMBER_IDS = "mid";
        public static final String MEMBERS_ACCESS_CONTROL_POLICY_IDS = "macp";
        public static final String MEMBER_TYPE_VALIDATED = "mtv";
        public static final String CONSISTENCY_STRATEGY = "csy";
        public static final String GROUP_NAME = "gn";
        public static final String LOCATION_SOURCE = "los";
        public static final String LOCATION_UPDATE_PERIOD = "lou";
        public static final String LOCATION_TARGET_ID = "lot";
        public static final String LOCATION_SERVER = "lor";
        public static final String LOCATION_CONTAINER_ID = "loi";
        public static final String LOCATION_CONTAINER_NAME = "lon";
        public static final String LOCATION_STATUS = "lost";
        public static final String SERVICE_ROLES = "svr";
        public static final String DESCRIPTION = "dc";
        public static final String CMD_TYPE = "cmt";
        public static final String MGMT_DEFINITION = "mgd";
        public static final String OBJECT_IDS = "obis";
        public static final String OBJECT_PATHS = "obps";
        public static final String NODE_ID = "ni";
        public static final String HOSTED_CSE_LINK = "hcl";
        public static final String CSE_BASE = "cb";
        public static final String M2M_EXT_ID = "mei";
        public static final String TRIGGER_RECIPIENT_ID = "tri";
        public static final String REQUEST_REACHABILITY = "rr";
        public static final String ORIGINATOR = "org";
        public static final String META_INFORMATION = "mi";
        public static final String REQUEST_STATUS = "rs";
        public static final String OPERATION_RESULT = "ors";
        public static final String REQUEST_ID = "rid";
        public static final String SCHEDULE_ELEMENT = "se";
        public static final String DEVICE_IDENTIFIER = "di";
        public static final String RULE_LINKS = "rlk";
        public static final String STATS_COLLECT_ID = "sci";
        public static final String COLLECTING_ENTITY_ID = "cei";
        public static final String COLLECTED_ENTITY_ID = "cdi";
        public static final String DEV_STATUS = "ss";
        public static final String STATS_RULE_STATUS = "srs";
        public static final String STAT_MODEL = "sm";
        public static final String COLLECT_PERIOD = "cp";
        public static final String EVENT_NOTIFICATION_CRITERIA = "enc";
        public static final String EXPIRATION_COUNTER = "exc";
        public static final String NOTIFICATION_URI = "nu";
        public static final String GROUP_ID = "gpi";
        public static final String NOTIFICATION_FORWARDING_URI = "nfu";
        public static final String BATCH_NOTIFY = "bn";
        public static final String RATE_LIMIT = "rl";
        public static final String PRE_SUBSCRIPTION_NOTIFY = "psn";
        public static final String PENDING_NOTIFICATION = "pn";
        public static final String NOTIFICATION_STORAGE_PRIORITY = "nsp";
        public static final String LATEST_NOTIFY = "ln";
        public static final String NOTIFICATION_CONTENT_TYPE = "nct";
        public static final String NOTIFICATION_EVENT_CAT = "nec";
        public static final String SUBSCRIBER_URI = "su";
        public static final String VERSION = "vr";
        public static final String URL = "url";
        public static final String URI = "uri";
        public static final String UPDATE = "ud";
        public static final String UPDATE_STATUS = "uds";
        public static final String INSTALL = "in";
        public static final String UNINSTALL = "un";
        public static final String INSTALL_STATUS = "ins";
        public static final String ACTIVATE = "act";
        public static final String DEACTIVATE = "dea";
        public static final String ACTIVE_STATUS = "acts";
        public static final String MEM_AVAILABLE = "mma";
        public static final String MEM_TOTAL = "mmt";
        public static final String AREA_NWK_TYPE = "ant";
        public static final String LIST_OF_DEVICES = "ldv";
        public static final String DEV_ID = "dvd";
        public static final String DEV_TYPE = "dvt";
        public static final String AREA_NWK_ID = "awi";
        public static final String SLEEP_INTERVAL = "sli";
        public static final String SLEEP_DURATION = "sld";
        public static final String LIST_OF_NEIGHBORS = "lnh";
        public static final String BATTERY_LEVEL = "btl";
        public static final String BATTERY_STATUS = "bts";
        public static final String DEVICE_LABEL = "dlb";
        public static final String MANUFACTURER = "man";
        public static final String MODEL = "mod";
        public static final String DEVICE_TYPE = "dty";
        public static final String FW_VERSION = "fwv";
        public static final String SW_VERSION = "swv";
        public static final String HW_VERSION = "hwv";
        public static final String CAPABILITY_NAME = "can";
        public static final String ATTACHED = "att";
        public static final String CAPABILITY_ACTION_STATUS = "cas";
        public static final String ENABLE = "ena";
        public static final String DISABLE = "dis";
        public static final String CURRENT_STATE = "cus";
        public static final String REBOOT = "rbo";
        public static final String FACTORY_RESET = "far";
        public static final String LOG_TYPE_ID = "lgt";
        public static final String LOG_DATA = "lgd";
        public static final String LOG_ACTION_STATUS = "lgs";
        public static final String LOG_STATUS = "lgst";
        public static final String LOG_START = "lga";
        public static final String LOG_STOP = "lgo";
        public static final String FIRMWARE_NAME = "fwn";
        public static final String SOFTWARE_NAME = "swn";
        public static final String CMDH_POLICY_NAME = "cpn";
        public static final String MGMT_LINK = "cmlk";
        public static final String ACTIVE_CMDH_POLICY_LINK = "acmlk";
        public static final String ORDER = "od";
        public static final String DEF_EC_VALUE = "dev";
        public static final String REQUEST_ORIGIN = "ror";
        public static final String REQUEST_CONTEXT = "rct";
        public static final String REQUEST_CONTEXT_NOTIFICATION = "rctn";
        public static final String REQUEST_CHARACTERISTICS = "rch";
        public static final String APPLICABLE_EVENT_CATEGORIES = "aecs";
        public static final String APPLICABLE_EVENT_CATEGORY = "aec";
        public static final String DEFAULT_REQUEST_EXP_TIME = "dqet";
        public static final String DEFAULT_RESULT_EXP_TIME = "dset";
        public static final String DEFAULT_OP_EXEC_TIME = "doet";
        public static final String DEFAULT_RESP_PERSISTENCE = "drp";
        public static final String DEFAULT_DEL_AGGREGATION = "dda";
        public static final String LIMITS_EVENT_CATEGORY = "lec";
        public static final String LIMITS_REQUEST_EXP_TIME = "lqet";
        public static final String LIMITS_RESULT_EXP_TIME = "lset";
        public static final String LIMITS_OP_EXEC_TIME = "loet";
        public static final String LIMITS_RESP_PERSISTENCE = "lrp";
        public static final String LIMITS_DEL_AGGREGATION = "lda";
        public static final String TARGET_NETWORK = "ttn";
        public static final String MIN_REQ_VOLUME = "mrv";
        public static final String BACK_OFF_PARAMETERS = "bop";
        public static final String OTHER_CONDITIONS = "ohc";
        public static final String MAX_BUFFER_SIZE = "mbfs";
        public static final String STORAGE_PRIORITY = "sgp";
        public static final String APPLICABLE_CRED_IDS = "apci";
        public static final String ALLOWED_APP_IDS = "aai";
        public static final String ALLOWED_AES = "aae";
        public static final String ACCESS_CONTROL_POLICY = "acp";
        public static final String ACCESS_CONTROL_POLICY_ANNC = "acpA";
        public static final String AE = "ae";
        public static final String AE_ANNC = "aeA";
        public static final String CONTAINER = "cnt";
        public static final String CONTAINER_ANNC = "cntA";
        public static final String LATEST = "la";
        public static final String OLDEST = "ol";
        public static final String CONTENT_INSTANCE = "cin";
        public static final String CONTENT_INSTANCE_ANNC = "cinA";
        public static final String DELIVERY = "dlv";
        public static final String EVENT_CONFIG = "evcg";
        public static final String EXEC_INSTANCE = "exin";
        public static final String FAN_OUT_POINT = "fopt";
        public static final String GROUP = "grp";
        public static final String GROUP_ANNC = "grpA";
        public static final String LOCATION_POLICY = "lcp";
        public static final String LOCATION_POLICY_ANNC = "lcpA";
        public static final String M2M_SERVICE_SUBSCRIPTION_PROFILE = "mssp";
        public static final String MGMT_CMD = "mgc";
        public static final String MGMT_OBJ = "mgo";
        public static final String MGMT_OBJ_ANNC = "mgoA";
        public static final String NODE = "nod";
        public static final String NODE_ANNC = "nodA";
        public static final String POLLING_CHANNEL = "pch";
        public static final String POLLING_CHANNEL_URI = "pcu";
        public static final String REMOTE_CSE = "csr";
        public static final String REMOTE_CSE_ANNC = "csrA";
        public static final String REQUEST = "req";
        public static final String SCHEDULE = "sch";
        public static final String SCHEDULE_ANNC = "schA";
        public static final String SERVICE_SUBSCRIBED_APP_RULE = "asar";
        public static final String SERVICE_SUBSCRIBED_NODE = "svsn";
        public static final String STATS_COLLECT = "stcl";
        public static final String STATS_CONFIG = "stcg";
        public static final String SUBSCRIPTION = "sub";
        public static final String FIRMWARE = "fwr";
        public static final String FIRMWARE_ANNC = "fwrA";
        public static final String SOFTWARE = "swr";
        public static final String SOFTWARE_ANNC = "swrA";
        public static final String MEMORY = "mem";
        public static final String MEMORY_ANNC = "memA";
        public static final String AREA_NWK_INFO = "ani";
        public static final String AREA_NWK_INFO_ANNC = "aniA";
        public static final String AREA_NWK_DEVICE_INFO = "andi";
        public static final String AREA_NWK_DEVICE_INFO_ANNC = "andiA";
        public static final String BATTERY = "bat";
        public static final String BATTERY_ANNC = "batA";
        public static final String DEVICE_INFO = "dvi";
        public static final String DEVICE_INFO_ANNC = "dviA";
        public static final String DEVICE_CAPABILITY = "dvc";
        public static final String DEVICE_CAPABILITY_ANNC = "dvcA";
        public static final String REBOOT_ANNC = "rboA";
        public static final String EVENT_LOG = "evl";
        public static final String EVENT_LOG_ANNC = "evlA";
        public static final String CMDH_POLICY = "cmp";
        public static final String ACTIVE_CMDH_POLICY = "acmp";
        public static final String CMDH_DEFAULTS = "cmdf";
        public static final String CMDH_DEF_EC_VALUE = "cmdv";
        public static final String CMDH_EC_DEF_PARAM_VALUES = "cmpv";
        public static final String CMDH_LIMITS = "cml";
        public static final String CMDH_NETWORK_ACCESS_RULES = "cmnr";
        public static final String CMDH_NW_ACCESS_RULE = "cmwr";
        public static final String CMDH_BUFFER = "cmbf";
        public static final String CREATED_BEFORE = "crb";
        public static final String CREATED_AFTER = "cra";
        public static final String MODIFIED_SINCE = "ms";
        public static final String UNMODIFIED_SINCE = "us";
        public static final String STATE_TAG_SMALLER = "sts";
        public static final String STATE_TAG_BIGGER = "stb";
        public static final String EXPIRE_BEFORE = "exb";
        public static final String EXPIRE_AFTER = "exa";
        public static final String SIZE_ABOVE = "sza";
        public static final String SIZE_BELOW = "szb";
        public static final String CONTENT_TYPE = "cty";
        public static final String LIMIT = "lim";
        public static final String ATTRIBUTE = "atr";
        public static final String RESOURCE_STATUS = "rss";
        public static final String NOTIFICATION_EVENT_TYPE = "net";
        public static final String OPERATION_MONITOR = "om";
        public static final String REPRESENTATION = "rep";
        public static final String FILTER_USAGE = "fu";
        public static final String EVENT_CAT_TYPE = "ect";
        public static final String EVENT_CAT_NO = "ecn";
        public static final String NUMBER = "num";
        public static final String DURATION = "dur";
        public static final String NOTIFICATION = "sgn";
        public static final String NOTIFICATION_EVENT = "nev";
        public static final String VERIFICATION_REQUEST = "vrq";
        public static final String SUBSCRIPTION_DELETION = "sud";
        public static final String SUBSCRIPTION_REFERENCE = "sur";
        public static final String ACCESS_ID = "aci";
        public static final String MSISDN = "msd";
        public static final String ACTION = "acn";
        public static final String STATUS = "sus";
        public static final String CHILD_RESOURCE = "ch";
        public static final String ACCESS_CONTROL_RULE = "acr";
        public static final String ACCESS_CONTROL_ORIGINATORS = "acor";
        public static final String ACCESS_CONTROL_OPERATIONS = "acop";
        public static final String ACCESS_CONTROL_CONTEXTS = "acco";
        public static final String ACCESS_CONTROL_WINDOW = "actw";
        public static final String ACCESS_CONTROL_IP_ADDRESSES = "acip";
        public static final String IPV4_ADDRESSES = "ipv4";
        public static final String IPV6_ADDRESSES = "ipv6";
        public static final String ACCESS_CONTROL_LOCATION_REGION = "aclr";
        public static final String COUNTRY_CODE = "accc";
        public static final String CIRC_REGION = "accr";
        public static final String VALUE = "val";
        public static final String TYPE = "typ";
        public static final String MAX_NR_OF_NOTIFY = "mnn";
        public static final String TIME_WINDOW = "tww";
        public static final String SCHEDULE_ENTRY = "sce";
        public static final String AGGREGATED_NOTIFICATION = "agn";
        public static final String ATTRIBUTE_LIST = "atrl";
        public static final String AGGREGATED_RESPONSE = "agr";
        public static final String RESOURCE = "rce";
        public static final String URI_LIST = "uril";
        public static final String ANY_ARG = "any";
        public static final String FILE_TYPE = "ftyp";
        public static final String USERNAME = "unm";
        public static final String PASSWORD = "pwd";
        public static final String FILESIZE = "fsi";
        public static final String TARGET_FILE = "tgf";
        public static final String DELAY_SECONDS = "dss";
        public static final String SUCCESS_URL = "surl";
        public static final String START_TIME = "stt";
        public static final String COMPLETE_TIME = "cpt";
        public static final String UUID = "uuid";
        public static final String EXECUTION_ENV_REF = "eer";
        public static final String RESET = "rst";
        public static final String UPLOAD = "uld";
        public static final String DOWNLOAD = "dld";
        public static final String SOFTWARE_INSTALL = "swin";
        public static final String SOFTWARE_UPDATE = "swup";
        public static final String SOFTWARE_UNINSTALL = "swun";
        public static final String TRACING_OPTION = "tcop";
        public static final String TRACING_INFO = "tcin";
        public static final String RESPONSE_TYPE_VALUE = "rtv";

        private static Map<String, String> longToShort = new HashMap<>();
        private static Map<String, String> shortToLong = new HashMap<>();

        static {
            Properties prop = new Properties();
            InputStream input = null;

            try {
                String config = "operation=op\n" +
                        "to=to\n" +
                        "from=fr\n" +
                        "requestIdentifier=rqi\n" +
                        "resourceType=ty\n" +
                        "name=nm\n" +
                        "primitiveContent=pc\n" +
                        "role=rol\n" +
                        "originatingTimestamp=ot\n" +
                        "requestExpirationTimestamp=rqet\n" +
                        "resultExpirationTimestamp=rset\n" +
                        "operationExecutionTime=oet\n" +
                        "responseType=rt\n" +
                        "resultPersistence=rp\n" +
                        "resultContent=rcn\n" +
                        "eventCategory=ec\n" +
                        "deliveryAggregation=da\n" +
                        "groupRequestIdentifier=gid\n" +
                        "filterCriteria=fc\n" +
                        "discoveryResultType=drt\n" +
                        "responseStatusCode=rsc\n" +
                        "requestPrimitive=rqp\n" +
                        "responsePrimitive=rsp\n" +
                        "accessControlPolicyIDs=acpi\n" +
                        "announcedAttribute=aa\n" +
                        "announceTo=at\n" +
                        "creationTime=ct\n" +
                        "expirationTime=et\n" +
                        "labels=lbl\n" +
                        "link=lnk\n" +
                        "lastModifiedTime=lt\n" +
                        "parentID=pi\n" +
                        "resourceID=ri\n" +
                        "stateTag=st\n" +
                        "resourceName=rn\n" +
                        "privileges=pv\n" +
                        "selfPrivileges=pvs\n" +
                        "App-ID=api\n" +
                        "AE-ID=aei\n" +
                        "appName=apn\n" +
                        "pointOfAccess=poa\n" +
                        "ontologyRef=or\n" +
                        "nodeLink=nl\n" +
                        "creator=cr\n" +
                        "maxNrOfInstances=mni\n" +
                        "maxByteSize=mbs\n" +
                        "maxInstanceAge=mia\n" +
                        "currentNrOfInstances=cni\n" +
                        "currentByteSize=cbs\n" +
                        "locationID=li\n" +
                        "contentInfo=cnf\n" +
                        "contentSize=cs\n" +
                        "content=con\n" +
                        "cseType=cst\n" +
                        "CSE-ID=csi\n" +
                        "supportedResourceType=srt\n" +
                        "notificationCongestionPolicy=ncp\n" +
                        "source=sr\n" +
                        "target=tg\n" +
                        "lifespan=ls\n" +
                        "eventCat=eca\n" +
                        "deliveryMetaData=dmd\n" +
                        "aggregatedRequest=arq\n" +
                        "eventID=evi\n" +
                        "eventType=evt\n" +
                        "eventStart=evs\n" +
                        "eventEnd=eve\n" +
                        "operationType=opt\n" +
                        "dataSize=ds\n" +
                        "execStatus=exs\n" +
                        "execResult=exr\n" +
                        "execDisable=exd\n" +
                        "execTarget=ext\n" +
                        "execMode=exm\n" +
                        "execFrequency=exf\n" +
                        "execDelay=exy\n" +
                        "execNumber=exn\n" +
                        "execReqArgs=exra\n" +
                        "execEnable=exe\n" +
                        "memberType=mt\n" +
                        "currentNrOfMembers=cnm\n" +
                        "maxNrOfMembers=mnm\n" +
                        "memberIDs=mid\n" +
                        "membersAccessControlPolicyIDs=macp\n" +
                        "memberTypeValidated=mtv\n" +
                        "consistencyStrategy=csy\n" +
                        "groupName=gn\n" +
                        "locationSource=los\n" +
                        "locationUpdatePeriod=lou\n" +
                        "locationTargetID=lot\n" +
                        "locationServer=lor\n" +
                        "locationContainerID=loi\n" +
                        "locationContainerName=lon\n" +
                        "locationStatus=lost\n" +
                        "serviceRoles=svr\n" +
                        "description=dc\n" +
                        "cmdType=cmt\n" +
                        "mgmtDefinition=mgd\n" +
                        "objectIDs=obis\n" +
                        "objectPaths=obps\n" +
                        "nodeID=ni\n" +
                        "hostedCSELink=hcl\n" +
                        "CSEBase=cb\n" +
                        "M2M-Ext-ID=mei\n" +
                        "Trigger-Recipient-ID=tri\n" +
                        "requestReachability=rr\n" +
                        "originator=org\n" +
                        "metaInformation=mi\n" +
                        "requestStatus=rs\n" +
                        "operationResult=ors\n" +
                        "requestID=rid\n" +
                        "scheduleElement=se\n" +
                        "deviceIdentifier=di\n" +
                        "ruleLinks=rlk\n" +
                        "statsCollectID=sci\n" +
                        "collectingEntityID=cei\n" +
                        "collectedEntityID=cdi\n" +
                        "devStatus=ss\n" +
                        "statsRuleStatus=srs\n" +
                        "statModel=sm\n" +
                        "collectPeriod=cp\n" +
                        "eventNotificationCriteria=enc\n" +
                        "expirationCounter=exc\n" +
                        "notificationURI=nu\n" +
                        "groupID=gpi\n" +
                        "notificationForwardingURI=nfu\n" +
                        "batchNotify=bn\n" +
                        "rateLimit=rl\n" +
                        "preSubscriptionNotify=psn\n" +
                        "pendingNotification=pn\n" +
                        "notificationStoragePriority=nsp\n" +
                        "latestNotify=ln\n" +
                        "notificationContentType=nct\n" +
                        "notificationEventCat=nec\n" +
                        "subscriberURI=su\n" +
                        "version=vr\n" +
                        "URL=url\n" +
                        "URI=uri\n" +
                        "update=ud\n" +
                        "updateStatus=uds\n" +
                        "install=in\n" +
                        "uninstall=un\n" +
                        "installStatus=ins\n" +
                        "activate=act\n" +
                        "deactivate=dea\n" +
                        "activeStatus=acts\n" +
                        "memAvailable=mma\n" +
                        "memTotal=mmt\n" +
                        "areaNwkType=ant\n" +
                        "listOfDevices=ldv\n" +
                        "devID=dvd\n" +
                        "devType=dvt\n" +
                        "areaNwkId=awi\n" +
                        "sleepInterval=sli\n" +
                        "sleepDuration=sld\n" +
                        "listOfNeighbors=lnh\n" +
                        "batteryLevel=btl\n" +
                        "batteryStatus=bts\n" +
                        "deviceLabel=dlb\n" +
                        "manufacturer=man\n" +
                        "model=mod\n" +
                        "deviceType=dty\n" +
                        "fwVersion=fwv\n" +
                        "swVersion=swv\n" +
                        "hwVersion=hwv\n" +
                        "capabilityName=can\n" +
                        "attached=att\n" +
                        "capabilityActionStatus=cas\n" +
                        "enable=ena\n" +
                        "disable=dis\n" +
                        "currentState=cus\n" +
                        "reboot=rbo\n" +
                        "factoryReset=far\n" +
                        "logTypeId=lgt\n" +
                        "logData=lgd\n" +
                        "logActionStatus=lgs\n" +
                        "logStatus=lgst\n" +
                        "logStart=lga\n" +
                        "logStop=lgo\n" +
                        "firmwareName=fwn\n" +
                        "softwareName=swn\n" +
                        "cmdhPolicyName=cpn\n" +
                        "mgmtLink=cmlk\n" +
                        "activeCmdhPolicyLink=acmlk\n" +
                        "order=od\n" +
                        "defEcValue=dev\n" +
                        "requestOrigin=ror\n" +
                        "requestContext=rct\n" +
                        "requestContextNotification=rctn\n" +
                        "requestCharacteristics=rch\n" +
                        "applicableEventCategories=aecs\n" +
                        "applicableEventCategory=aec\n" +
                        "defaultRequestExpTime=dqet\n" +
                        "defaultResultExpTime=dset\n" +
                        "defaultOpExecTime=doet\n" +
                        "defaultRespPersistence=drp\n" +
                        "defaultDelAggregation=dda\n" +
                        "limitsEventCategory=lec\n" +
                        "limitsRequestExpTime=lqet\n" +
                        "limitsResultExpTime=lset\n" +
                        "limitsOpExecTime=loet\n" +
                        "limitsRespPersistence=lrp\n" +
                        "limitsDelAggregation=lda\n" +
                        "targetNetwork=ttn\n" +
                        "minReqVolume=mrv\n" +
                        "backOffParameters=bop\n" +
                        "otherConditions=ohc\n" +
                        "maxBufferSize=mbfs\n" +
                        "storagePriority=sgp\n" +
                        "applicableCredIDs=apci\n" +
                        "allowedApp-IDs=aai\n" +
                        "allowedAEs=aae\n" +
                        "accessControlPolicy=acp\n" +
                        "accessControlPolicyAnnc=acpA\n" +
                        "AE=ae\n" +
                        "AEAnnc=aeA\n" +
                        "container=cnt\n" +
                        "containerAnnc=cntA\n" +
                        "latest=la\n" +
                        "oldest=ol\n" +
                        "contentInstance=cin\n" +
                        "contentInstanceAnnc=cinA\n" +
                        "delivery=dlv\n" +
                        "eventConfig=evcg\n" +
                        "execInstance=exin\n" +
                        "fanOutPoint=fopt\n" +
                        "group=grp\n" +
                        "groupAnnc=grpA\n" +
                        "locationPolicy=lcp\n" +
                        "locationPolicyAnnc=lcpA\n" +
                        "m2mServiceSubscriptionProfile=mssp\n" +
                        "mgmtCmd=mgc\n" +
                        "mgmtObj=mgo\n" +
                        "mgmtObjAnnc=mgoA\n" +
                        "node=nod\n" +
                        "nodeAnnc=nodA\n" +
                        "pollingChannel=pch\n" +
                        "pollingChannelURI=pcu\n" +
                        "remoteCSE=csr\n" +
                        "remoteCSEAnnc=csrA\n" +
                        "request=req\n" +
                        "schedule=sch\n" +
                        "scheduleAnnc=schA\n" +
                        "serviceSubscribedAppRule=asar\n" +
                        "serviceSubscribedNode=svsn\n" +
                        "statsCollect=stcl\n" +
                        "statsConfig=stcg\n" +
                        "subscription=sub\n" +
                        "firmware=fwr\n" +
                        "firmwareAnnc=fwrA\n" +
                        "software=swr\n" +
                        "softwareAnnc=swrA\n" +
                        "memory=mem\n" +
                        "memoryAnnc=memA\n" +
                        "areaNwkInfo=ani\n" +
                        "areaNwkInfoAnnc=aniA\n" +
                        "areaNwkDeviceInfo=andi\n" +
                        "areaNwkDeviceInfoAnnc=andiA\n" +
                        "battery=bat\n" +
                        "batteryAnnc=batA\n" +
                        "deviceInfo=dvi\n" +
                        "deviceInfoAnnc=dviA\n" +
                        "deviceCapability=dvc\n" +
                        "deviceCapabilityAnnc=dvcA\n" +
                        "rebootAnnc=rboA\n" +
                        "eventLog=evl\n" +
                        "eventLogAnnc=evlA\n" +
                        "cmdhPolicy=cmp\n" +
                        "activeCmdhPolicy=acmp\n" +
                        "cmdhDefaults=cmdf\n" +
                        "cmdhDefEcValue=cmdv\n" +
                        "cmdhEcDefParamValues=cmpv\n" +
                        "cmdhLimits=cml\n" +
                        "cmdhNetworkAccessRules=cmnr\n" +
                        "cmdhNwAccessRule=cmwr\n" +
                        "cmdhBuffer=cmbf\n" +
                        "createdBefore=crb\n" +
                        "createdAfter=cra\n" +
                        "modifiedSince=ms\n" +
                        "unmodifiedSince=us\n" +
                        "stateTagSmaller=sts\n" +
                        "stateTagBigger=stb\n" +
                        "expireBefore=exb\n" +
                        "expireAfter=exa\n" +
                        "sizeAbove=sza\n" +
                        "sizeBelow=szb\n" +
                        "contentType=cty\n" +
                        "limit=lim\n" +
                        "attribute=atr\n" +
                        "resourceStatus=rss\n" +
                        "notificationEventType=net\n" +
                        "operationMonitor=om\n" +
                        "representation=rep\n" +
                        "filterUsage=fu\n" +
                        "eventCatType=ect\n" +
                        "eventCatNo=ecn\n" +
                        "number=num\n" +
                        "duration=dur\n" +
                        "notification=sgn\n" +
                        "notificationEvent=nev\n" +
                        "verificationRequest=vrq\n" +
                        "subscriptionDeletion=sud\n" +
                        "subscriptionReference=sur\n" +
                        "accessId=aci\n" +
                        "MSISDN=msd\n" +
                        "action=acn\n" +
                        "status=sus\n" +
                        "childResource=ch\n" +
                        "accessControlRule=acr\n" +
                        "accessControlOriginators=acor\n" +
                        "accessControlOperations=acop\n" +
                        "accessControlContexts=acco\n" +
                        "accessControlWindow=actw\n" +
                        "accessControlIpAddresses=acip\n" +
                        "ipv4Addresses=ipv4\n" +
                        "ipv6Addresses=ipv6\n" +
                        "accessControlLocationRegion=aclr\n" +
                        "countryCode=accc\n" +
                        "circRegion=accr\n" +
                        "value=val\n" +
                        "type=typ\n" +
                        "maxNrOfNotify=mnn\n" +
                        "timeWindow=tww\n" +
                        "scheduleEntry=sce\n" +
                        "aggregatedNotification=agn\n" +
                        "attributeList=atrl\n" +
                        "aggregatedResponse=agr\n" +
                        "resource=rce\n" +
                        "URIList=uril\n" +
                        "anyArg=any\n" +
                        "fileType=ftyp\n" +
                        "username=unm\n" +
                        "password=pwd\n" +
                        "filesize=fsi\n" +
                        "targetFile=tgf\n" +
                        "delaySeconds=dss\n" +
                        "successURL=surl\n" +
                        "startTime=stt\n" +
                        "completeTime=cpt\n" +
                        "UUID=uuid\n" +
                        "executionEnvRef=eer\n" +
                        "reset=rst\n" +
                        "upload=uld\n" +
                        "download=dld\n" +
                        "softwareInstall=swin\n" +
                        "softwareUpdate=swup\n" +
                        "softwareUninstall=swun\n" +
                        "tracingOption=tcop\n" +
                        "tracingInfo=tcin\n" +
                        "responseTypeValue=rtv";
                input = new ByteArrayInputStream(config.getBytes());
                prop.load(input);
                for (Map.Entry<Object, Object> entry : prop.entrySet()) {
                    String key = entry.getKey().toString();
                    String value = entry.getValue().toString();
                    longToShort.put(key, value);
                    shortToLong.put(value, key);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static final String longToShort(String longName) {
            return longToShort.containsKey(longName) ? longToShort.get(longName) : longName;
        }

        public static final String shortTolong(String shortName) {
            return shortToLong.containsKey(shortName) ? shortToLong.get(shortName) : shortName;
        }
    }


    public static class CoAP {
        public static final String NAME = "coap";
        public static final String VERSION = "1";

        public static class MIME {
            public static int APPLICATION_XML = 41;
            public static int APPLICATION_JSON = 50;

            public static int VND_ONEM2M_RES_XML = 10000;
            public static int VND_ONEM2M_RES_JSON = 10001;


            public static int VND_ONEM2M_NTFY_XML = 10002;
            public static int VND_ONEM2M_NTFY_JSON = 10003;

            public static int VND_ONEM2M_ATTRS_XML = 10004;
            public static int VND_ONEM2M_ATTRS_JSON = 10005;

            public static int VND_ONEM2M_PREQ_XML = 10006;
            public static int VND_ONEM2M_PREQ_JSON = 10007;

            public static int VND_ONEM2M_PRSP_XML = 10008;
            public static int VND_ONEM2M_PRSP_JSON = 10009;

            private static Map<Integer, String> map2String = new HashMap<>();
            private static Map<String, Integer> map2Int = new HashMap<>();


            static {
                map2String.put(APPLICATION_XML, "application/xml");
                map2String.put(APPLICATION_JSON, "application/json");
                map2String.put(VND_ONEM2M_RES_XML, "vnd.onem2m-res+xml");
                map2String.put(VND_ONEM2M_RES_JSON, "vnd.onem2m-res+json");
                map2String.put(VND_ONEM2M_NTFY_XML, "vnd.onem2m-ntfy+xml");
                map2String.put(VND_ONEM2M_NTFY_JSON, "vnd.onem2m-ntfy+json");
                map2String.put(VND_ONEM2M_ATTRS_XML, "vnd.onem2m-attrs+xml");
                map2String.put(VND_ONEM2M_ATTRS_JSON, "vnd.onem2m-attrs+json");
                map2String.put(VND_ONEM2M_PREQ_XML, "vnd.onem2m-PREQ+xml");
                map2String.put(VND_ONEM2M_PREQ_JSON, "vnd.onem2m-PREQ+json");
                map2String.put(VND_ONEM2M_PRSP_XML, "vnd.onem2m-PRSP+xml");
                map2String.put(VND_ONEM2M_PRSP_JSON, "vnd.onem2m-PRSP+json");

                for (Map.Entry<Integer, String> entry : map2String.entrySet()) {
                    map2Int.put(entry.getValue(), entry.getKey());
                }
            }

            public static final String map2String(int number) {
                return map2String.get(number);
            }

            public static int map2Int(String number) {
                return map2Int.get(number);
            }
        }

        public static class Option {
            public final static int ONEM2M_FR = 256;
            public final static int ONEM2M_RQI = 257;
            public final static int ONEM2M_NM = 258;
            public final static int ONEM2M_OT = 259;
            public final static int ONEM2M_RQET = 260;
            public final static int ONEM2M_RSET = 261;
            public final static int ONEM2M_OET = 262;
            public final static int ONEM2M_RTURI = 263;
            public final static int ONEM2M_EC = 264;
            public final static int ONEM2M_RSC = 265;
            public final static int ONEM2M_GID = 266;
            public final static int ONEM2M_TY = 267;

            private static Map<String, Integer> map = new HashMap<>();

            static {
                map.put(Name.FROM, ONEM2M_FR);
                map.put(Name.REQUEST_IDENTIFIER, ONEM2M_RQI);
                map.put(Name.NAME, ONEM2M_NM);
                map.put(Name.ORIGINATING_TIMESTAMP, ONEM2M_OT);
                map.put(Name.REQUEST_EXPIRATION_TIMESTAMP, ONEM2M_RQET);
                map.put(Name.RESULT_EXPIRATION_TIMESTAMP, ONEM2M_RSET);
                map.put(Name.OPERATION_EXECUTION_TIME, ONEM2M_OET);
                map.put(Name.RESPONSE_TYPE_VALUE, ONEM2M_RTURI);
                map.put(Name.EVENT_CATEGORY, ONEM2M_EC);
                map.put(Name.RESPONSE_STATUS_CODE, ONEM2M_RSC);
                map.put(Name.GROUP_REQUEST_IDENTIFIER, ONEM2M_GID);
                map.put(Name.RESOURCE_TYPE, ONEM2M_TY);
            }

            public static int map2Int(String name) {
                return map.get(name);
            }
        }
    }

    public static class Http {
        public static final String NAME = "http";

        public static class MIME extends OneM2M.MIME {
            public static final String APPLICATION_XML = "application/xml";
            public static final String APPLICATION_JSON = "application/json";
        }

        public static class Header {
            public static final String HOST = "host";
            public static final String ACCEPT = "accept";
            public static final String CONTENT_TYPE = "content-type";
            public static final String CONTENT_LOCATION = "content-location";
            public static final String CONTENT_LENGTH = "content-length";
            public static final String ETAG = "etag";
            public static final String X_M2M_ORIGIN = "X-M2M-Origin";
            public static final String X_M2M_RI = "X-M2M-RI";
            public static final String X_M2M_NM = "X-M2M-NM";
            public static final String X_M2M_GID = "X-M2M-GID";
            public static final String X_M2M_RTU = "X-M2M-RTU";
            public static final String X_M2M_OT = "X-M2M-OT";
            public static final String X_M2M_RST = "X-M2M-RST";
            public static final String X_M2M_RET = "X-M2M-RET";
            public static final String X_M2M_OET = "X-M2M-OET";
            public static final String X_M2M_EC = "X-M2M-EC";
            public static final String X_M2M_RSC = "X-M2M-RSC";

            private static Map<String, String> map = new HashMap<>();

            static {
                map.put(Name.FROM, X_M2M_ORIGIN);
                map.put(Name.REQUEST_IDENTIFIER, X_M2M_RI);
                map.put(Name.NAME, X_M2M_NM);
                map.put(Name.ORIGINATING_TIMESTAMP, X_M2M_OT);
                map.put(Name.REQUEST_EXPIRATION_TIMESTAMP, X_M2M_RET);
                map.put(Name.RESULT_EXPIRATION_TIMESTAMP, X_M2M_RST);
                map.put(Name.OPERATION_EXECUTION_TIME, X_M2M_OET);
                map.put(Name.RESPONSE_TYPE_VALUE, X_M2M_RTU);
                map.put(Name.EVENT_CATEGORY, X_M2M_EC);
                map.put(Name.RESPONSE_STATUS_CODE, X_M2M_RSC);
                map.put(Name.GROUP_REQUEST_IDENTIFIER, X_M2M_GID);
            }

            public static final String map(String name) {
                return map.containsKey(name) ? map.get(name) : name;
            }
        }
    }

    public static class Path {
        public static final String toToPathMapping(String to) {
            if (to == null || to.isEmpty()) {
                return "";
            } else if (to.startsWith("//")) {
                return "/_/" + to.substring(2);
            } else if (to.startsWith("/")) {
                return "/~" + to;
            } else {
                return "/" + to;
            }
        }

        public static final String pathToToMapping(String path) {
            if (path == null || path.isEmpty()) {
                return "";
            } else if (path.startsWith("/_/")) {
                return "//" + path.substring(3);
            } else if (path.startsWith("/~/"))
                return path.substring(2);
            else {
                return path.substring(1);
            }
        }
    }

    public static class Time {
        private static final String FORMAT = "uuuuMMdd:HHmmss";
        private static final String SEPARATOR = "T";
        private static final String FAKE_SEPARATOR = ":";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

        private LocalDateTime localDateTime;
        private long relativeTime;
        private boolean isRelativetime;

        public static final String currentTime() {
            LocalDateTime localDateTime = LocalDateTime.now();
            String time = localDateTime.format(FORMATTER);
            return time.replace(FAKE_SEPARATOR, SEPARATOR);
        }

        public static LocalDateTime parse(String timeStamp) {
            try {
                timeStamp = timeStamp.replace(SEPARATOR, FAKE_SEPARATOR);
                return LocalDateTime.parse(timeStamp, FORMATTER);
            } catch (Exception e) {
                throw new Onem2mIncorrectTimeStampError(timeStamp);
            }
        }

        public static final String format(LocalDateTime localDateTime) {
            String time = localDateTime.format(FORMATTER);
            return time.replace(FAKE_SEPARATOR, SEPARATOR);
        }

        public Time(String localDateTime) {
            this.localDateTime = LocalDateTime.parse(localDateTime);
            isRelativetime = false;
        }


        public Time(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
            isRelativetime = false;
        }

        public Time(long relativeTime) {
            this.relativeTime = relativeTime;
            isRelativetime = true;
        }

        public String toString() {
            return isRelativetime ? String.valueOf(relativeTime) : format(localDateTime);
        }
    }
}
