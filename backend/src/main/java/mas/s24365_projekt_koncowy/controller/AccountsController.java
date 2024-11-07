package mas.s24365_projekt_koncowy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mas.s24365_projekt_koncowy.model.dto.account.AccountDetails;
import mas.s24365_projekt_koncowy.model.dto.account.AccountDetailsOwner;
import mas.s24365_projekt_koncowy.model.dto.account.AccountDetailsTransaction;
import mas.s24365_projekt_koncowy.service.AccountsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Accounts controller")
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping("/{accountNumber}")
    @Operation(
            summary = "Get account details",
            description = "Endpoint returns details of an account with the given IBAN number",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account details returned"),
                    @ApiResponse(responseCode = "404", description = "Account with the given IBAN does not exist",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    AccountDetails getAccountDetails(
            @Parameter(description = "Account number in IBAN format", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String accountNumber
    ) {
        return accountsService.getAccountDetails(accountNumber);
    }

    @GetMapping("/{accountNumber}/owners")
    @Operation(
            summary = "Get account owners",
            description = "Endpoint returns a list of account owners",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account owners returned"),
                    @ApiResponse(responseCode = "404", description = "Account with the given IBAN does not exist",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    List<AccountDetailsOwner> getAccountOwners(
            @Parameter(description = "Account number in IBAN format", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String accountNumber
    ) {
        return accountsService.getAccountOwners(accountNumber);
    }

    @GetMapping("/{accountNumber}/transactions")
    @Operation(
            summary = "Get account transactions",
            description = "Endpoint returns a list of transactions associated with the account",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Account transactions returned"),
                    @ApiResponse(responseCode = "204", description = "Account was found, but no transactions are associated with it",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Account with the given IBAN does not exist",
                            content = @Content(schema = @Schema(hidden = true)))
            })
    List<AccountDetailsTransaction> getAccountTransactions(
            @Parameter(description = "Account number in IBAN format", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String accountNumber
    ) {
        return accountsService.getAccountTransactions(accountNumber);
    }
}
