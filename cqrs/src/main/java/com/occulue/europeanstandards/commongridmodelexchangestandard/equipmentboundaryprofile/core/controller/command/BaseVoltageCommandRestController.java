/**
 * ***************************************************************************** Turnstone Biologics
 * Confidential
 *
 * <p>2018 Turnstone Biologics All Rights Reserved.
 *
 * <p>This file is subject to the terms and conditions defined in file 'license.txt', which is part
 * of this source code package.
 *
 * <p>Contributors : Turnstone Biologics - General Release
 * ****************************************************************************
 */
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.core.controller.command;

import com.occulue.api.*;
import com.occulue.command.*;
import com.occulue.controller.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * Implements Spring Controller command CQRS processing for entity BaseVoltage.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/BaseVoltage")
public class BaseVoltageCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a BaseVoltage. if not key provided, calls create, otherwise calls save
   *
   * @param BaseVoltage baseVoltage
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateBaseVoltageCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          BaseVoltageBusinessDelegate.getBaseVoltageInstance().createBaseVoltage(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a BaseVoltage. if no key provided, calls create, otherwise calls save
   *
   * @param BaseVoltage baseVoltage
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateBaseVoltageCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateBaseVoltageCommand
      // -----------------------------------------------
      completableFuture =
          BaseVoltageBusinessDelegate.getBaseVoltageInstance().updateBaseVoltage(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "BaseVoltageController:update() - successfully update BaseVoltage - " + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a BaseVoltage entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID baseVoltageId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteBaseVoltageCommand command = new DeleteBaseVoltageCommand(baseVoltageId);

    try {
      BaseVoltageBusinessDelegate delegate = BaseVoltageBusinessDelegate.getBaseVoltageInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING, "Successfully deleted BaseVoltage with key " + command.getBaseVoltageId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save NominalVoltage on BaseVoltage
   *
   * @param command AssignNominalVoltageToBaseVoltageCommand
   */
  @PutMapping("/assignNominalVoltage")
  public void assignNominalVoltage(@RequestBody AssignNominalVoltageToBaseVoltageCommand command) {
    try {
      BaseVoltageBusinessDelegate.getBaseVoltageInstance().assignNominalVoltage(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign NominalVoltage", exc);
    }
  }

  /**
   * unassign NominalVoltage on BaseVoltage
   *
   * @param command UnAssignNominalVoltageFromBaseVoltageCommand
   */
  @PutMapping("/unAssignNominalVoltage")
  public void unAssignNominalVoltage(
      @RequestBody(required = true) UnAssignNominalVoltageFromBaseVoltageCommand command) {
    try {
      BaseVoltageBusinessDelegate.getBaseVoltageInstance().unAssignNominalVoltage(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign NominalVoltage", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected BaseVoltage baseVoltage = null;
  private static final Logger LOGGER =
      Logger.getLogger(BaseVoltageCommandRestController.class.getName());
}
