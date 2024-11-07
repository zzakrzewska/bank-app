package mas.s24365_projekt_koncowy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mas.s24365_projekt_koncowy.model.dto.client.ClientDetails;
import mas.s24365_projekt_koncowy.model.dto.bank.BankClientAccount;
import mas.s24365_projekt_koncowy.service.ClientsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Clients controller")
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class ClientsController {

    private final ClientsService clientsService;

    @GetMapping("/{clientPESEL}")
    @Operation(
            summary = "Get client by PESEL number",
            description = "Endpoint returns details of a client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Client details returned"),
                    @ApiResponse(responseCode = "404", description = "Client with the given PESEL number does not exist",
                                    content = @Content(schema = @Schema(hidden = true)))
            })
    ClientDetails getClientByPESEL(
            @Parameter(description = "Client PESEL", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String clientPESEL
    ) {
        return clientsService.getClientByPesel(clientPESEL);
    }

    @GetMapping("/{clientPESEL}/accounts")
    @Operation(
            summary = "Get client's accounts list",
            description = "Endpoint returns a list of accounts associated with the given client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of client's accounts returned"),
                    @ApiResponse(responseCode = "204", description = "Client was found, but no accounts are associated with them",
                                    content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Client with the given PESEL number does not exist",
                                    content = @Content(schema = @Schema(hidden = true)))
            })
    List<BankClientAccount> getClientAccounts(
            @Parameter(description = "Client PESEL", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String clientPESEL
    ) {
        return clientsService.getClientAccounts(clientPESEL);
    }

}
