package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.MemberDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateMemberFlow;
import za.ac.nwu.ac.logic.flow.FetchMemberMilesFlow;
import za.ac.nwu.ac.logic.flow.addAndSubtractMilesFlow;

import java.util.List;

@RestController
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

    @GetMapping("{firstName}")
    @ApiOperation(value = "Fetches the miles for a specifed member",notes = "Fetches the member details for the specified First Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Member found"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> getMilesByName(
            @ApiParam(value = "The first name of the desired member",
                    example = "Ahmed",
                    name = "first name",
                    required = true)
            @PathVariable("firstName") String name){
        MemberDto member = fetchMemberMilesFlow.getMilesByMemberName(name);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true,member);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    @PostMapping("")
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

    @PutMapping("{miles}")
    @ApiOperation(value = "adds miles to members mile balance",notes = "updates miles amount to the corresponding member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Miles added"),
            @ApiResponse(code = 400, message = "Bad request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource not found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<MemberDto>> addMiles(
            @ApiParam(value = "The amount to add to miles balance.",
                    example = "100",
                    name = "miles",
                    required = true)
            @PathVariable("miles") final long miles,

            @ApiParam(value = "The first name of member to add miles.",
                    example = "Ahmed",
                    name = "member name")
            @RequestParam("firstName") final String firstName
    ){
        MemberDto memberDto = AddAndSubtractMilesFlow.addMiles(miles,firstName);
        GeneralResponse<MemberDto> response = new GeneralResponse<>(true,memberDto);
        return new ResponseEntity<>(response,HttpStatus.OK);


    }
}
