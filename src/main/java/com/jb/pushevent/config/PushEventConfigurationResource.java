package com.jb.pushevent.config;


import com.google.inject.Inject;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import sonia.scm.api.v2.resources.ErrorDto;
import sonia.scm.web.VndMediaType;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@OpenAPIDefinition(tags = {
  @Tag(name = "Push Event Plugin", description = "Push Event plugin provided endpoints")
})
@Path(PushEventConfigurationResource.PUSHEVENT_CONFIG_PATH_V2)
public class PushEventConfigurationResource {

  static final String PUSHEVENT_CONFIG_PATH_V2 = "v2/config/pushevent";

  private final PushEventMapper mapper;
  private final PushEventConfigurationStore store;

  @Inject
  public PushEventConfigurationResource(PushEventMapper mapper, PushEventConfigurationStore store) {
    this.mapper = mapper;
    this.store = store;
  }

  @GET
  @Path("/")
  @Produces({MediaType.APPLICATION_JSON})
  @Operation(
    summary = "Get global push event configuration",
    description = "Returns the global push even configuration.",
    tags = "Push Event Plugin",
    operationId = "push_event_get_global_config"
  )
  @ApiResponse(
    responseCode = "200",
    description = "success",
    content = @Content(
      mediaType = MediaType.APPLICATION_JSON,
      schema = @Schema(implementation = PushEventConfigurationDto.class)
    )
  )
  @ApiResponse(responseCode = "401", description = "not authenticated / invalid credentials")
  @ApiResponse(responseCode = "403", description = "not authorized, the current user has no privileges to read the configuration")
  @ApiResponse(
    responseCode = "500",
    description = "internal server error",
    content = @Content(
      mediaType = VndMediaType.ERROR_TYPE,
      schema = @Schema(implementation = ErrorDto.class)
    )
  )
  public Response get() {
    return Response.ok(mapper.map(store.get())).build();
  }

  @PUT
  @Path("")
  @Consumes({MediaType.APPLICATION_JSON})
  @Operation(
    summary = "Update global push event configuration",
    description = "Modifies the global push event configuration.",
    tags = "Push Event Plugin",
    operationId = "push_event_put_global_config"
  )
  @ApiResponse(responseCode = "204", description = "update success")
  @ApiResponse(responseCode = "400", description = "invalid body")
  @ApiResponse(responseCode = "401", description = "not authenticated / invalid credentials")
  @ApiResponse(responseCode = "403", description = "not authorized, the current user does not have the privilege to change the configuration")
  @ApiResponse(
    responseCode = "500",
    description = "internal server error",
    content = @Content(
      mediaType = VndMediaType.ERROR_TYPE,
      schema = @Schema(implementation = ErrorDto.class)
    )
  )
  public Response update(@Valid PushEventConfigurationDto updatedConfig) {
    store.update(mapper.map(updatedConfig));
    return Response.noContent().build();
  }
}
