package mas.s24365_projekt_koncowy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mas.s24365_projekt_koncowy.model.dto.client.Client;
import mas.s24365_projekt_koncowy.service.BankService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Bank controller")
@RequestMapping(value = "/bank", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
class BanksController {

    private final BankService bankService;

    @GetMapping("/{bankBIC}/clients")
    @Operation(
            summary = "Get bank clients list",
            description = "Endpoint returns a list of clients associated with the given bank",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of clients returned"),
                    @ApiResponse(responseCode = "204", description = "Bank was found, but no clients are associated with it",
                            content = @Content(schema = @Schema(hidden = true))),
                    @ApiResponse(responseCode = "404", description = "Bank with the given BIC code does not exist",
                                    content = @Content(schema = @Schema(hidden = true)))
    })
    List<Client> getBankClients(
            @Parameter(description = "Bank BIC code", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string"))
            @PathVariable String bankBIC
    ) {
        return bankService.getBankClients(bankBIC);
    }

}
