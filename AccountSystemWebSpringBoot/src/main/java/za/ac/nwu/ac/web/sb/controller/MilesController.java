package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.persistence.Member;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateMemberFlow;
import za.ac.nwu.ac.logic.flow.FetchMemberMilesFlow;
import za.ac.nwu.ac.logic.flow.addAndSubtractMilesFlow;

import java.util.List;

@RestController("Member Controller")
@RequestMapping("members")
public class MilesController {
    private final FetchMemberMilesFlow fetchMemberMilesFlow;
    private final CreateMemberFlow createMemberFlow;
    private final addAndSubtractMilesFlow AddAndSubtractMilesFlow;

    @Autowired
    public MilesController(FetchMemberMilesFlow fetchMemberMilesFlow, CreateMemberFlow createMemberFlow, addAndSubtractMilesFlow addAndSubtractMilesFlow) {
        this.fetchMemberMilesFlow = fetchMemberMilesFlow;
        this.createMemberFlow = createMemberFlow;
        this.AddAndSubtractMilesFlow = addAndSubtractMilesFlow;
    }

    @GetMapping("/allMembers")
    @ApiOperation(value = "Gets all members currently registered",notes ="returns all members")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account types returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<MemberDto>>> getAll(){
        List<MemberDto> member = fetchMemberMilesFlow.getAllMembers();
        GeneralResponse<List<MemberDto>> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{firstName}")
    @ApiOperation(value = "Create new member.", notes = "creates a new member in DB")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The account was created successfully", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<MemberDto>> create(
            @ApiParam(value = "Request body to create new Member", required = true)
            @RequestBody MemberDto memberDto) {
        MemberDto memberResponse = createMemberFlow.create(memberDto);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true, memberResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{firstName2}")
    @ApiOperation(value = "adds miles to a members mile balance",notes = "updates miles amount to the corresponding member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Miles added"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<Integer>> addMiles(
            @ApiParam(value = "The amount to add to miles balance.",
                    name = "miles",
                    example = "100",
                    required = true)
            @RequestParam("miles") final long miles,
            @ApiParam(value = "The name of member to subtract miles.",
                    example = "Ahmed",
                    name = "firstName2",
                    required = true)
            @RequestParam("firstName2") final String firstName2
    ){//if answer is 0:unsuccessful if answer is 1: successful
        int answer = AddAndSubtractMilesFlow.addMiles(miles,firstName2);
        GeneralResponse<Integer> response = new GeneralResponse<>(true,answer);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PutMapping("{firstName}")
    @ApiOperation(value = "subtracts miles from a members mile balance",notes = "updates miles amount to the corresponding member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Miles subtracted"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<Integer>> subtractMiles(
            @ApiParam(value = "The amount to subtract from miles balance.",
                    name = "miles",
                    example = "100",
                    required = true)
            @RequestParam("miles") final long miles,
            @ApiParam(value = "The name of member to subtract miles.",
            example = "Ahmed",
            name = "firstName",
            required = true)
            @RequestParam("firstName") final String firstName
    ){//if answer is 0:unsuccessful if answer is 1: successful
        int answer = AddAndSubtractMilesFlow.subtractMiles(miles,firstName);
        GeneralResponse<Integer> response = new GeneralResponse<>(true,answer);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @GetMapping("{fname}")
    @ApiOperation(value = "Fetches the specified AccountType.",notes = "Fetches the AccountType corresponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal found"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)

    })
    public ResponseEntity<GeneralResponse<MemberDto>> getMiles(
            @ApiParam(value = "The name that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "fname",
                    required = true)
            @PathVariable("fname") final String fname){
        MemberDto member = fetchMemberMilesFlow.getMilesByMemberName(fname);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true,member);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
