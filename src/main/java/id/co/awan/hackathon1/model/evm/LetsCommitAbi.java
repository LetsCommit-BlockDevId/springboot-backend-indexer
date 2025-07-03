package id.co.awan.hackathon1.model.evm;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.StaticArray5;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tuples.generated.Tuple8;
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
public class LetsCommitAbi extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_ATTENDSESSION = "attendSession";

    public static final String FUNC_CLAIM = "claim";

    public static final String FUNC_CLAIMFIRSTPORTION = "claimFirstPortion";

    public static final String FUNC_CLAIMUNATTENDEDFEES = "claimUnattendedFees";

    public static final String FUNC_CREATEEVENT = "createEvent";

    public static final String FUNC_ENROLLANDATTEND = "enrollAndAttend";

    public static final String FUNC_ENROLLEVENT = "enrollEvent";

    public static final String FUNC_EVENTID = "eventId";

    public static final String FUNC_EVENTIDCLAIM = "eventIdClaim";

    public static final String FUNC_EVENTIDENROLL = "eventIdEnroll";

    public static final String FUNC_EVENTS = "events";

    public static final String FUNC_GETENROLLEDPARTICIPANTSCOUNT = "getEnrolledParticipantsCount";

    public static final String FUNC_GETEVENT = "getEvent";

    public static final String FUNC_GETORGANIZERCLAIMABLEAMOUNT = "getOrganizerClaimableAmount";

    public static final String FUNC_GETORGANIZERCLAIMEDAMOUNT = "getOrganizerClaimedAmount";

    public static final String FUNC_GETORGANIZERVESTEDAMOUNT = "getOrganizerVestedAmount";

    public static final String FUNC_GETORGANIZERVESTEDAMOUNTPERSESSION = "getOrganizerVestedAmountPerSession";

    public static final String FUNC_GETPARTICIPANTATTENDANCE = "getParticipantAttendance";

    public static final String FUNC_GETPARTICIPANTATTENDEDSESSIONSCOUNT = "getParticipantAttendedSessionsCount";

    public static final String FUNC_GETPARTICIPANTCOMMITMENTFEE = "getParticipantCommitmentFee";

    public static final String FUNC_GETPROTOCOLTVL = "getProtocolTVL";

    public static final String FUNC_GETSESSION = "getSession";

    public static final String FUNC_GETSESSIONATTENDEDCOUNT = "getSessionAttendedCount";

    public static final String FUNC_GETSESSIONUNATTENDEDCLAIMTIMESTAMP = "getSessionUnattendedClaimTimestamp";

    public static final String FUNC_HASCLAIMEDUNATTENDEDFEES = "hasClaimedUnattendedFees";

    public static final String FUNC_HASPARTICIPANTATTENDEDSESSION = "hasParticipantAttendedSession";

    public static final String FUNC_HASSESSIONCODE = "hasSessionCode";

    public static final String FUNC_ISPARTICIPANTENROLLED = "isParticipantEnrolled";

    public static final String FUNC_MIDRXTOKEN = "mIDRXToken";

    public static final String FUNC_MAXSESSIONSPEREVENT = "maxSessionsPerEvent";

    public static final String FUNC_ORGANIZERCLAIMABLEAMOUNT = "organizerClaimableAmount";

    public static final String FUNC_ORGANIZERCLAIMEDAMOUNT = "organizerClaimedAmount";

    public static final String FUNC_ORGANIZERVESTEDAMOUNT = "organizerVestedAmount";

    public static final String FUNC_PARTICIPANTS = "participants";

    public static final String FUNC_PREVIEWUNATTENDEDFEESFORSESSION = "previewUnattendedFeesForSession";

    public static final String FUNC_PROTOCOLADMIN = "protocolAdmin";

    public static final String FUNC_PROTOCOLTVL = "protocolTVL";

    public static final String FUNC_SESSIONCODES = "sessionCodes";

    public static final String FUNC_SESSIONUNATTENDEDCLAIMTIMESTAMP = "sessionUnattendedClaimTimestamp";

    public static final String FUNC_SESSIONS = "sessions";

    public static final String FUNC_SETMAXSESSIONSPEREVENT = "setMaxSessionsPerEvent";

    public static final String FUNC_SETSESSIONCODE = "setSessionCode";

    public static final org.web3j.abi.datatypes.Event ATTENDEVENTSESSION_EVENT = new org.web3j.abi.datatypes.Event("AttendEventSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event CREATEEVENT_EVENT = new org.web3j.abi.datatypes.Event("CreateEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event CREATEEVENTMETADATA_EVENT = new org.web3j.abi.datatypes.Event("CreateEventMetadata", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<StaticArray5<Utf8String>>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event CREATESESSION_EVENT = new org.web3j.abi.datatypes.Event("CreateSession", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event ENROLLEVENT_EVENT = new org.web3j.abi.datatypes.Event("EnrollEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event GENERATESESSIONTOKEN_EVENT = new org.web3j.abi.datatypes.Event("GenerateSessionToken", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event ORGANIZERCLAIMUNATTENDED_EVENT = new org.web3j.abi.datatypes.Event("OrganizerClaimUnattended", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Uint8>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event ORGANIZERFIRSTCLAIM_EVENT = new org.web3j.abi.datatypes.Event("OrganizerFirstClaim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event ORGANIZERLASTCLAIM_EVENT = new org.web3j.abi.datatypes.Event("OrganizerLastClaim", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final org.web3j.abi.datatypes.Event SETSESSIONCODE_EVENT = new org.web3j.abi.datatypes.Event("SetSessionCode", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint8>(true) {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final CustomError ENDSALEDATEINPAST_ERROR = new CustomError("EndSaleDateInPast", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError EVENTDOESNOTEXIST_ERROR = new CustomError("EventDoesNotExist", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final CustomError EVENTFEEALREADYCLAIMED_ERROR = new CustomError("EventFeeAlreadyClaimed", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError EVENTNOTINSALEPERIOD_ERROR = new CustomError("EventNotInSalePeriod", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError INSUFFICIENTALLOWANCE_ERROR = new CustomError("InsufficientAllowance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final CustomError INSUFFICIENTBALANCE_ERROR = new CustomError("InsufficientBalance", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final CustomError INVALIDSALEDATERANGE_ERROR = new CustomError("InvalidSaleDateRange", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError INVALIDSESSIONCODE_ERROR = new CustomError("InvalidSessionCode", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError INVALIDSESSIONCODELENGTH_ERROR = new CustomError("InvalidSessionCodeLength", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError INVALIDSESSIONINDEX_ERROR = new CustomError("InvalidSessionIndex", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError LASTSESSIONMUSTBEAFTERSALEEND_ERROR = new CustomError("LastSessionMustBeAfterSaleEnd", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError MAXSESSIONSZERO_ERROR = new CustomError("MaxSessionsZero", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOCLAIMABLEAMOUNT_ERROR = new CustomError("NoClaimableAmount", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOUNATTENDEDPARTICIPANTS_ERROR = new CustomError("NoUnattendedParticipants", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOVESTEDAMOUNTTORELEASE_ERROR = new CustomError("NoVestedAmountToRelease", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOVESTEDCOMMITMENTFEES_ERROR = new CustomError("NoVestedCommitmentFees", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOTEVENTORGANIZER_ERROR = new CustomError("NotEventOrganizer", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOTPROTOCOLADMIN_ERROR = new CustomError("NotProtocolAdmin", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError NOTWITHINSESSIONTIME_ERROR = new CustomError("NotWithinSessionTime", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError PARTICIPANTALREADYATTENDED_ERROR = new CustomError("ParticipantAlreadyAttended", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError PARTICIPANTALREADYENROLLED_ERROR = new CustomError("ParticipantAlreadyEnrolled", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError PARTICIPANTNOTENROLLED_ERROR = new CustomError("ParticipantNotEnrolled", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError SESSIONCODEALREADYSET_ERROR = new CustomError("SessionCodeAlreadySet", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError SESSIONCODEEMPTY_ERROR = new CustomError("SessionCodeEmpty", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError SESSIONCODENOTSET_ERROR = new CustomError("SessionCodeNotSet", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError SESSIONNOTENDED_ERROR = new CustomError("SessionNotEnded", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError STARTSALEDATEINPAST_ERROR = new CustomError("StartSaleDateInPast", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError TOKENTRANSFERFAILED_ERROR = new CustomError("TokenTransferFailed", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError TOTALSESSIONSEXCEEDSMAX_ERROR = new CustomError("TotalSessionsExceedsMax", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final CustomError TOTALSESSIONSZERO_ERROR = new CustomError("TotalSessionsZero", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final CustomError UNATTENDEDFEESALREADYCLAIMED_ERROR = new CustomError("UnattendedFeesAlreadyClaimed", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected LetsCommitAbi(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LetsCommitAbi(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LetsCommitAbi(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LetsCommitAbi(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> attendSession(Uint256 _eventId,
            Uint8 _sessionIndex, Utf8String _sessionCode) {
        final Function function = new Function(
                FUNC_ATTENDSESSION, 
                Arrays.<Type>asList(_eventId, _sessionIndex, _sessionCode), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> claim() {
        final Function function = new Function(
                FUNC_CLAIM, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> claimFirstPortion(Uint256 _eventId) {
        final Function function = new Function(
                FUNC_CLAIMFIRSTPORTION, 
                Arrays.<Type>asList(_eventId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> claimUnattendedFees(Uint256 _eventId,
            Uint8 _sessionIndex) {
        final Function function = new Function(
                FUNC_CLAIMUNATTENDEDFEES, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createEvent(Utf8String title,
            Utf8String description, Utf8String location, Utf8String imageUri, Uint256 priceAmount,
            Uint256 commitmentAmount, Uint8 maxParticipant, Uint256 startSaleDate,
            Uint256 endSaleDate, StaticArray5<Utf8String> tags, Array<Session> _sessions) {
        final Function function = new Function(
                FUNC_CREATEEVENT, 
                Arrays.<Type>asList(title, description, location, imageUri, priceAmount, commitmentAmount, maxParticipant, startSaleDate, endSaleDate, tags, _sessions),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> enrollAndAttend() {
        final Function function = new Function(
                FUNC_ENROLLANDATTEND, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> enrollEvent(Uint256 _eventId) {
        final Function function = new Function(
                FUNC_ENROLLEVENT, 
                Arrays.<Type>asList(_eventId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Uint256> eventId() {
        final Function function = new Function(FUNC_EVENTID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> eventIdClaim() {
        final Function function = new Function(FUNC_EVENTIDCLAIM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> eventIdEnroll() {
        final Function function = new Function(FUNC_EVENTIDENROLL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Tuple8<Address, Uint256, Uint256, Uint8, Uint256, Uint256, Uint256, Uint256>> events(
            Uint256 param0) {
        final Function function = new Function(FUNC_EVENTS, 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple8<Address, Uint256, Uint256, Uint8, Uint256, Uint256, Uint256, Uint256>>(function,
                new Callable<Tuple8<Address, Uint256, Uint256, Uint8, Uint256, Uint256, Uint256, Uint256>>() {
                    @Override
                    public Tuple8<Address, Uint256, Uint256, Uint8, Uint256, Uint256, Uint256, Uint256> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<Address, Uint256, Uint256, Uint8, Uint256, Uint256, Uint256, Uint256>(
                                (Address) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint8) results.get(3), 
                                (Uint256) results.get(4), 
                                (Uint256) results.get(5), 
                                (Uint256) results.get(6), 
                                (Uint256) results.get(7));
                    }
                });
    }

    public RemoteFunctionCall<Uint256> getEnrolledParticipantsCount(Uint256 _eventId) {
        final Function function = new Function(FUNC_GETENROLLEDPARTICIPANTSCOUNT, 
                Arrays.<Type>asList(_eventId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Event> getEvent(Uint256 _eventId) {
        final Function function = new Function(FUNC_GETEVENT, 
                Arrays.<Type>asList(_eventId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Event>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getOrganizerClaimableAmount(Uint256 _eventId,
            Address _organizer) {
        final Function function = new Function(FUNC_GETORGANIZERCLAIMABLEAMOUNT, 
                Arrays.<Type>asList(_eventId, _organizer), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getOrganizerClaimedAmount(Uint256 _eventId,
            Address _organizer) {
        final Function function = new Function(FUNC_GETORGANIZERCLAIMEDAMOUNT, 
                Arrays.<Type>asList(_eventId, _organizer), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getOrganizerVestedAmount(Uint256 _eventId,
            Address _organizer) {
        final Function function = new Function(FUNC_GETORGANIZERVESTEDAMOUNT, 
                Arrays.<Type>asList(_eventId, _organizer), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getOrganizerVestedAmountPerSession(Uint256 _eventId) {
        final Function function = new Function(FUNC_GETORGANIZERVESTEDAMOUNTPERSESSION, 
                Arrays.<Type>asList(_eventId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getParticipantAttendance(Uint256 _eventId,
            Address _participant, Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_GETPARTICIPANTATTENDANCE, 
                Arrays.<Type>asList(_eventId, _participant, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint8> getParticipantAttendedSessionsCount(Uint256 _eventId,
            Address _participant) {
        final Function function = new Function(FUNC_GETPARTICIPANTATTENDEDSESSIONSCOUNT, 
                Arrays.<Type>asList(_eventId, _participant), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getParticipantCommitmentFee(Uint256 _eventId,
            Address _participant) {
        final Function function = new Function(FUNC_GETPARTICIPANTCOMMITMENTFEE, 
                Arrays.<Type>asList(_eventId, _participant), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getProtocolTVL() {
        final Function function = new Function(FUNC_GETPROTOCOLTVL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Session> getSession(Uint256 _eventId, Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_GETSESSION, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Session>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getSessionAttendedCount(Uint256 _eventId,
            Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_GETSESSIONATTENDEDCOUNT, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> getSessionUnattendedClaimTimestamp(Uint256 _eventId,
            Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_GETSESSIONUNATTENDEDCLAIMTIMESTAMP, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Bool> hasClaimedUnattendedFees(Uint256 _eventId,
            Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_HASCLAIMEDUNATTENDEDFEES, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Bool> hasParticipantAttendedSession(Uint256 _eventId,
            Address _participant, Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_HASPARTICIPANTATTENDEDSESSION, 
                Arrays.<Type>asList(_eventId, _participant, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Bool> hasSessionCode(Uint256 _eventId, Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_HASSESSIONCODE, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Bool> isParticipantEnrolled(Uint256 _eventId, Address _participant) {
        final Function function = new Function(FUNC_ISPARTICIPANTENROLLED, 
                Arrays.<Type>asList(_eventId, _participant), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Address> mIDRXToken() {
        final Function function = new Function(FUNC_MIDRXTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint8> maxSessionsPerEvent() {
        final Function function = new Function(FUNC_MAXSESSIONSPEREVENT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> organizerClaimableAmount(Address param0, Uint256 param1) {
        final Function function = new Function(FUNC_ORGANIZERCLAIMABLEAMOUNT, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> organizerClaimedAmount(Address param0, Uint256 param1) {
        final Function function = new Function(FUNC_ORGANIZERCLAIMEDAMOUNT, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> organizerVestedAmount(Address param0, Uint256 param1) {
        final Function function = new Function(FUNC_ORGANIZERVESTEDAMOUNT, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Tuple3<Uint256, Uint256, Uint8>> participants(Uint256 param0,
            Address param1) {
        final Function function = new Function(FUNC_PARTICIPANTS, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple3<Uint256, Uint256, Uint8>>(function,
                new Callable<Tuple3<Uint256, Uint256, Uint8>>() {
                    @Override
                    public Tuple3<Uint256, Uint256, Uint8> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Uint256, Uint256, Uint8>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint8) results.get(2));
                    }
                });
    }

    public RemoteFunctionCall<Tuple4<Uint256, Uint256, Uint256, Uint256>> previewUnattendedFeesForSession(
            Uint256 _eventId, Uint8 _sessionIndex) {
        final Function function = new Function(FUNC_PREVIEWUNATTENDEDFEESFORSESSION, 
                Arrays.<Type>asList(_eventId, _sessionIndex), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<Uint256, Uint256, Uint256, Uint256>>(function,
                new Callable<Tuple4<Uint256, Uint256, Uint256, Uint256>>() {
                    @Override
                    public Tuple4<Uint256, Uint256, Uint256, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Uint256, Uint256, Uint256, Uint256>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint256) results.get(3));
                    }
                });
    }

    public RemoteFunctionCall<Address> protocolAdmin() {
        final Function function = new Function(FUNC_PROTOCOLADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> protocolTVL() {
        final Function function = new Function(FUNC_PROTOCOLTVL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Utf8String> sessionCodes(Uint256 param0, Uint8 param1) {
        final Function function = new Function(FUNC_SESSIONCODES, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Uint256> sessionUnattendedClaimTimestamp(Uint256 param0,
            Uint8 param1) {
        final Function function = new Function(FUNC_SESSIONUNATTENDEDCLAIMTIMESTAMP, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteFunctionCall<Tuple3<Uint256, Uint256, Uint256>> sessions(Uint256 param0,
            Uint8 param1) {
        final Function function = new Function(FUNC_SESSIONS, 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<Uint256, Uint256, Uint256>>(function,
                new Callable<Tuple3<Uint256, Uint256, Uint256>>() {
                    @Override
                    public Tuple3<Uint256, Uint256, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Uint256, Uint256, Uint256>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2));
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setMaxSessionsPerEvent(Uint8 newMaxSessions) {
        final Function function = new Function(
                FUNC_SETMAXSESSIONSPEREVENT, 
                Arrays.<Type>asList(newMaxSessions), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSessionCode(Uint256 _eventId,
            Uint8 _sessionIndex, Utf8String _code) {
        final Function function = new Function(
                FUNC_SETSESSIONCODE, 
                Arrays.<Type>asList(_eventId, _sessionIndex, _code), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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
    public static LetsCommitAbi load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new LetsCommitAbi(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LetsCommitAbi load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LetsCommitAbi(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LetsCommitAbi load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new LetsCommitAbi(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LetsCommitAbi load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LetsCommitAbi(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class Session extends StaticStruct {
        public Uint256 startSessionTime;

        public Uint256 endSessionTime;

        public Uint256 attendedCount;

        public Session(Uint256 startSessionTime, Uint256 endSessionTime, Uint256 attendedCount) {
            super(startSessionTime, endSessionTime, attendedCount);
            this.startSessionTime = startSessionTime;
            this.endSessionTime = endSessionTime;
            this.attendedCount = attendedCount;
        }
    }

    public static class Event extends StaticStruct {
        public Address organizer;

        public Uint256 priceAmount;

        public Uint256 commitmentAmount;

        public Uint8 totalSession;

        public Uint256 startSaleDate;

        public Uint256 endSaleDate;

        public Uint256 lastSessionEndTime;

        public Uint256 enrolledCount;

        public Event(Address organizer, Uint256 priceAmount, Uint256 commitmentAmount,
                Uint8 totalSession, Uint256 startSaleDate, Uint256 endSaleDate,
                Uint256 lastSessionEndTime, Uint256 enrolledCount) {
            super(organizer, priceAmount, commitmentAmount, totalSession, startSaleDate, endSaleDate, lastSessionEndTime, enrolledCount);
            this.organizer = organizer;
            this.priceAmount = priceAmount;
            this.commitmentAmount = commitmentAmount;
            this.totalSession = totalSession;
            this.startSaleDate = startSaleDate;
            this.endSaleDate = endSaleDate;
            this.lastSessionEndTime = lastSessionEndTime;
            this.enrolledCount = enrolledCount;
        }
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
