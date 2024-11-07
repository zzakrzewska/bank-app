package mas.s24365_projekt_koncowy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mas.s24365_projekt_koncowy.model.dto.transfer.CreateTransfer;
import mas.s24365_projekt_koncowy.model.dto.transfer.TransferDetails;
import mas.s24365_projekt_koncowy.model.dto.transfer.TransferReceiver;
import mas.s24365_projekt_koncowy.model.dto.transfer.TransferSender;
import mas.s24365_projekt_koncowy.service.TransferService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Transactions controller")
@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class TransactionsController {

    private final TransferService transferService;

    @GetMapping("/{transactionId}")
    @Operation(
            summary = "Get transaction details by reference number",
            description = "Endpoint returns details of a transaction",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction details returned"),
                    @ApiResponse(responseCode = "404", description = "Transaction with the given reference number does not exist",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    TransferDetails getTransactionDetails(
            @Parameter(description = "Transaction reference", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String transactionId
    ) {
        return transferService.getTransactionDetails(transactionId);
    }

    @GetMapping("/{transactionId}/receiver")
    @Operation(
            summary = "Get transaction receiver by transaction reference number",
            description = "Endpoint returns the receiver of a transaction",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction receiver returned"),
                    @ApiResponse(responseCode = "404", description = "Transaction with the given reference number does not exist",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    TransferReceiver getTransactionReceiver(
            @Parameter(description = "Transaction reference", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String transactionId
    ) {
        return transferService.getTransactionReceiver(transactionId);
    }

    @GetMapping("/{transactionId}/sender")
    @Operation(
            summary = "Get transaction sender by transaction reference number",
            description = "Endpoint returns the sender of a transaction",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction sender returned"),
                    @ApiResponse(responseCode = "404", description = "Transaction with the given reference number does not exist",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    TransferSender getTransactionSender(
            @Parameter(description = "Transaction reference", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String transactionId
    ) {
        return transferService.getTransactionSender(transactionId);
    }

    @PostMapping
    @Operation(
            summary = "Create a new transaction",
            description = "Endpoint creates a new transaction and returns its reference ID",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Transaction created successfully"),
                    @ApiResponse(responseCode = "400", description = "Transaction creation request is structured incorrectly",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Sender/Receiver account not found",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    Long createTransaction(@RequestBody @Valid CreateTransfer createTransfer) {
        return transferService.createTransfer(createTransfer);
    }

    @DeleteMapping("/{transactionId}")
    @Operation(
            summary = "Cancel a new transaction",
            description = "Endpoint cancels a transaction",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transaction cancelled successfully"),
                    @ApiResponse(responseCode = "400", description = "Transaction cannnot be cancelled",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Transaction not found",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    boolean createTransaction(
            @Parameter(description = "Transaction reference", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String transactionId) {
        return transferService.cancelTransfer(transactionId);
    }
}
