package id.co.awan.hackathon1.model.evm;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.StaticArray5;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class IEventIndexerAbi extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final Event ATTENDEVENTSESSION_EVENT = new Event("AttendEventSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event CREATEEVENT_EVENT = new Event("CreateEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event CREATEEVENTMETADATA_EVENT = new Event("CreateEventMetadata", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<StaticArray5<Utf8String>>() {}));
    ;

    public static final Event CREATESESSION_EVENT = new Event("CreateSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ENROLLEVENT_EVENT = new Event("EnrollEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event GENERATESESSIONTOKEN_EVENT = new Event("GenerateSessionToken", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event ORGANIZERCLAIMUNATTENDED_EVENT = new Event("OrganizerClaimUnattended", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ORGANIZERFIRSTCLAIM_EVENT = new Event("OrganizerFirstClaim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ORGANIZERLASTCLAIM_EVENT = new Event("OrganizerLastClaim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SETSESSIONCODE_EVENT = new Event("SetSessionCode", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IEventIndexerAbi(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IEventIndexerAbi(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IEventIndexerAbi(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IEventIndexerAbi(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<AttendEventSessionEventResponse> getAttendEventSessionEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ATTENDEVENTSESSION_EVENT, transactionReceipt);
        ArrayList<AttendEventSessionEventResponse> responses = new ArrayList<AttendEventSessionEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            AttendEventSessionEventResponse typedResponse = new AttendEventSessionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
            typedResponse.participant = (Address) eventValues.getIndexedValues().get(2);
            typedResponse.attendToken = (DynamicBytes) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static AttendEventSessionEventResponse getAttendEventSessionEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ATTENDEVENTSESSION_EVENT, log);
        AttendEventSessionEventResponse typedResponse = new AttendEventSessionEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
        typedResponse.participant = (Address) eventValues.getIndexedValues().get(2);
        typedResponse.attendToken = (DynamicBytes) eventValues.getNonIndexedValues().get(0);
        return typedResponse;
    }

    public Flowable<AttendEventSessionEventResponse> attendEventSessionEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getAttendEventSessionEventFromLog(log));
    }

    public Flowable<AttendEventSessionEventResponse> attendEventSessionEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ATTENDEVENTSESSION_EVENT));
        return attendEventSessionEventFlowable(filter);
    }

    public static List<CreateEventEventResponse> getCreateEventEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CREATEEVENT_EVENT, transactionReceipt);
        ArrayList<CreateEventEventResponse> responses = new ArrayList<CreateEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateEventEventResponse typedResponse = new CreateEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.organizer = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.priceAmount = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.commitmentAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.totalSession = (Uint8) eventValues.getNonIndexedValues().get(2);
            typedResponse.maxParticipant = (Uint8) eventValues.getNonIndexedValues().get(3);
            typedResponse.startSaleDate = (Uint256) eventValues.getNonIndexedValues().get(4);
            typedResponse.endSaleDate = (Uint256) eventValues.getNonIndexedValues().get(5);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CreateEventEventResponse getCreateEventEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CREATEEVENT_EVENT, log);
        CreateEventEventResponse typedResponse = new CreateEventEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.organizer = (Address) eventValues.getIndexedValues().get(1);
        typedResponse.priceAmount = (Uint256) eventValues.getNonIndexedValues().get(0);
        typedResponse.commitmentAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
        typedResponse.totalSession = (Uint8) eventValues.getNonIndexedValues().get(2);
        typedResponse.maxParticipant = (Uint8) eventValues.getNonIndexedValues().get(3);
        typedResponse.startSaleDate = (Uint256) eventValues.getNonIndexedValues().get(4);
        typedResponse.endSaleDate = (Uint256) eventValues.getNonIndexedValues().get(5);
        return typedResponse;
    }

    public Flowable<CreateEventEventResponse> createEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCreateEventEventFromLog(log));
    }

    public Flowable<CreateEventEventResponse> createEventEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEEVENT_EVENT));
        return createEventEventFlowable(filter);
    }

    public static List<CreateEventMetadataEventResponse> getCreateEventMetadataEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CREATEEVENTMETADATA_EVENT, transactionReceipt);
        ArrayList<CreateEventMetadataEventResponse> responses = new ArrayList<CreateEventMetadataEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateEventMetadataEventResponse typedResponse = new CreateEventMetadataEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.title = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.description = (Utf8String) eventValues.getNonIndexedValues().get(1);
            typedResponse.location = (Utf8String) eventValues.getNonIndexedValues().get(2);
            typedResponse.imageUri = (Utf8String) eventValues.getNonIndexedValues().get(3);
            typedResponse.tag = (StaticArray5<Utf8String>) eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CreateEventMetadataEventResponse getCreateEventMetadataEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CREATEEVENTMETADATA_EVENT, log);
        CreateEventMetadataEventResponse typedResponse = new CreateEventMetadataEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.title = (Utf8String) eventValues.getNonIndexedValues().get(0);
        typedResponse.description = (Utf8String) eventValues.getNonIndexedValues().get(1);
        typedResponse.location = (Utf8String) eventValues.getNonIndexedValues().get(2);
        typedResponse.imageUri = (Utf8String) eventValues.getNonIndexedValues().get(3);
        typedResponse.tag = (StaticArray5<Utf8String>) eventValues.getNonIndexedValues().get(4);
        return typedResponse;
    }

    public Flowable<CreateEventMetadataEventResponse> createEventMetadataEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCreateEventMetadataEventFromLog(log));
    }

    public Flowable<CreateEventMetadataEventResponse> createEventMetadataEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATEEVENTMETADATA_EVENT));
        return createEventMetadataEventFlowable(filter);
    }

    public static List<CreateSessionEventResponse> getCreateSessionEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(CREATESESSION_EVENT, transactionReceipt);
        ArrayList<CreateSessionEventResponse> responses = new ArrayList<CreateSessionEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            CreateSessionEventResponse typedResponse = new CreateSessionEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
            typedResponse.title = (Utf8String) eventValues.getNonIndexedValues().get(0);
            typedResponse.startSessionTime = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.endSessionTime = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static CreateSessionEventResponse getCreateSessionEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(CREATESESSION_EVENT, log);
        CreateSessionEventResponse typedResponse = new CreateSessionEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
        typedResponse.title = (Utf8String) eventValues.getNonIndexedValues().get(0);
        typedResponse.startSessionTime = (Uint256) eventValues.getNonIndexedValues().get(1);
        typedResponse.endSessionTime = (Uint256) eventValues.getNonIndexedValues().get(2);
        return typedResponse;
    }

    public Flowable<CreateSessionEventResponse> createSessionEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getCreateSessionEventFromLog(log));
    }

    public Flowable<CreateSessionEventResponse> createSessionEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CREATESESSION_EVENT));
        return createSessionEventFlowable(filter);
    }

    public static List<EnrollEventEventResponse> getEnrollEventEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ENROLLEVENT_EVENT, transactionReceipt);
        ArrayList<EnrollEventEventResponse> responses = new ArrayList<EnrollEventEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            EnrollEventEventResponse typedResponse = new EnrollEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.participant = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.debitAmount = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static EnrollEventEventResponse getEnrollEventEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ENROLLEVENT_EVENT, log);
        EnrollEventEventResponse typedResponse = new EnrollEventEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.participant = (Address) eventValues.getIndexedValues().get(1);
        typedResponse.debitAmount = (Uint256) eventValues.getNonIndexedValues().get(0);
        return typedResponse;
    }

    public Flowable<EnrollEventEventResponse> enrollEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getEnrollEventEventFromLog(log));
    }

    public Flowable<EnrollEventEventResponse> enrollEventEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ENROLLEVENT_EVENT));
        return enrollEventEventFlowable(filter);
    }

    public static List<GenerateSessionTokenEventResponse> getGenerateSessionTokenEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(GENERATESESSIONTOKEN_EVENT, transactionReceipt);
        ArrayList<GenerateSessionTokenEventResponse> responses = new ArrayList<GenerateSessionTokenEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            GenerateSessionTokenEventResponse typedResponse = new GenerateSessionTokenEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
            typedResponse.token = (Utf8String) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static GenerateSessionTokenEventResponse getGenerateSessionTokenEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(GENERATESESSIONTOKEN_EVENT, log);
        GenerateSessionTokenEventResponse typedResponse = new GenerateSessionTokenEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
        typedResponse.token = (Utf8String) eventValues.getNonIndexedValues().get(0);
        return typedResponse;
    }

    public Flowable<GenerateSessionTokenEventResponse> generateSessionTokenEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getGenerateSessionTokenEventFromLog(log));
    }

    public Flowable<GenerateSessionTokenEventResponse> generateSessionTokenEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GENERATESESSIONTOKEN_EVENT));
        return generateSessionTokenEventFlowable(filter);
    }

    public static List<OrganizerClaimUnattendedEventResponse> getOrganizerClaimUnattendedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ORGANIZERCLAIMUNATTENDED_EVENT, transactionReceipt);
        ArrayList<OrganizerClaimUnattendedEventResponse> responses = new ArrayList<OrganizerClaimUnattendedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OrganizerClaimUnattendedEventResponse typedResponse = new OrganizerClaimUnattendedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
            typedResponse.unattendedPerson = (Uint8) eventValues.getNonIndexedValues().get(0);
            typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.claimAmount = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OrganizerClaimUnattendedEventResponse getOrganizerClaimUnattendedEventFromLog(
            Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ORGANIZERCLAIMUNATTENDED_EVENT, log);
        OrganizerClaimUnattendedEventResponse typedResponse = new OrganizerClaimUnattendedEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
        typedResponse.unattendedPerson = (Uint8) eventValues.getNonIndexedValues().get(0);
        typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(1);
        typedResponse.claimAmount = (Uint256) eventValues.getNonIndexedValues().get(2);
        return typedResponse;
    }

    public Flowable<OrganizerClaimUnattendedEventResponse> organizerClaimUnattendedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOrganizerClaimUnattendedEventFromLog(log));
    }

    public Flowable<OrganizerClaimUnattendedEventResponse> organizerClaimUnattendedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGANIZERCLAIMUNATTENDED_EVENT));
        return organizerClaimUnattendedEventFlowable(filter);
    }

    public static List<OrganizerFirstClaimEventResponse> getOrganizerFirstClaimEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ORGANIZERFIRSTCLAIM_EVENT, transactionReceipt);
        ArrayList<OrganizerFirstClaimEventResponse> responses = new ArrayList<OrganizerFirstClaimEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OrganizerFirstClaimEventResponse typedResponse = new OrganizerFirstClaimEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.claimAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OrganizerFirstClaimEventResponse getOrganizerFirstClaimEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ORGANIZERFIRSTCLAIM_EVENT, log);
        OrganizerFirstClaimEventResponse typedResponse = new OrganizerFirstClaimEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(0);
        typedResponse.claimAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
        return typedResponse;
    }

    public Flowable<OrganizerFirstClaimEventResponse> organizerFirstClaimEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOrganizerFirstClaimEventFromLog(log));
    }

    public Flowable<OrganizerFirstClaimEventResponse> organizerFirstClaimEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGANIZERFIRSTCLAIM_EVENT));
        return organizerFirstClaimEventFlowable(filter);
    }

    public static List<OrganizerLastClaimEventResponse> getOrganizerLastClaimEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ORGANIZERLASTCLAIM_EVENT, transactionReceipt);
        ArrayList<OrganizerLastClaimEventResponse> responses = new ArrayList<OrganizerLastClaimEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OrganizerLastClaimEventResponse typedResponse = new OrganizerLastClaimEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.claimAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OrganizerLastClaimEventResponse getOrganizerLastClaimEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ORGANIZERLASTCLAIM_EVENT, log);
        OrganizerLastClaimEventResponse typedResponse = new OrganizerLastClaimEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(0);
        typedResponse.claimAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
        return typedResponse;
    }

    public Flowable<OrganizerLastClaimEventResponse> organizerLastClaimEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOrganizerLastClaimEventFromLog(log));
    }

    public Flowable<OrganizerLastClaimEventResponse> organizerLastClaimEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORGANIZERLASTCLAIM_EVENT));
        return organizerLastClaimEventFlowable(filter);
    }

    public static List<SetSessionCodeEventResponse> getSetSessionCodeEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(SETSESSIONCODE_EVENT, transactionReceipt);
        ArrayList<SetSessionCodeEventResponse> responses = new ArrayList<SetSessionCodeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetSessionCodeEventResponse typedResponse = new SetSessionCodeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
            typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
            typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.releasedAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public static SetSessionCodeEventResponse getSetSessionCodeEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(SETSESSIONCODE_EVENT, log);
        SetSessionCodeEventResponse typedResponse = new SetSessionCodeEventResponse();
        typedResponse.log = log;
        typedResponse.eventId = (Uint256) eventValues.getIndexedValues().get(0);
        typedResponse.session = (Uint8) eventValues.getIndexedValues().get(1);
        typedResponse.organizer = (Address) eventValues.getNonIndexedValues().get(0);
        typedResponse.releasedAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
        return typedResponse;
    }

    public Flowable<SetSessionCodeEventResponse> setSessionCodeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getSetSessionCodeEventFromLog(log));
    }

    public Flowable<SetSessionCodeEventResponse> setSessionCodeEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETSESSIONCODE_EVENT));
        return setSessionCodeEventFlowable(filter);
    }

    @Deprecated
    public static IEventIndexerAbi load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IEventIndexerAbi(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IEventIndexerAbi load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IEventIndexerAbi(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IEventIndexerAbi load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IEventIndexerAbi(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IEventIndexerAbi load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IEventIndexerAbi(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class AttendEventSessionEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Uint8 session;

        public Address participant;

        public DynamicBytes attendToken;
    }

    public static class CreateEventEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Address organizer;

        public Uint256 priceAmount;

        public Uint256 commitmentAmount;

        public Uint8 totalSession;

        public Uint8 maxParticipant;

        public Uint256 startSaleDate;

        public Uint256 endSaleDate;
    }

    public static class CreateEventMetadataEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Utf8String title;

        public Utf8String description;

        public Utf8String location;

        public Utf8String imageUri;

        public StaticArray5<Utf8String> tag;
    }

    public static class CreateSessionEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Uint8 session;

        public Utf8String title;

        public Uint256 startSessionTime;

        public Uint256 endSessionTime;
    }

    public static class EnrollEventEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Address participant;

        public Uint256 debitAmount;
    }

    public static class GenerateSessionTokenEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Uint8 session;

        public Utf8String token;
    }

    public static class OrganizerClaimUnattendedEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Uint8 session;

        public Uint8 unattendedPerson;

        public Address organizer;

        public Uint256 claimAmount;
    }

    public static class OrganizerFirstClaimEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Address organizer;

        public Uint256 claimAmount;
    }

    public static class OrganizerLastClaimEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Address organizer;

        public Uint256 claimAmount;
    }

    public static class SetSessionCodeEventResponse extends BaseEventResponse {
        public Uint256 eventId;

        public Uint8 session;

        public Address organizer;

        public Uint256 releasedAmount;
    }
}
