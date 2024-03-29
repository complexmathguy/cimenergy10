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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.operationallimits.controller.command;

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
 * Implements Spring Controller command CQRS processing for entity ApparentPowerLimit.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/ApparentPowerLimit")
public class ApparentPowerLimitCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a ApparentPowerLimit. if not key provided, calls create, otherwise calls save
   *
   * @param ApparentPowerLimit apparentPowerLimit
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(
      @RequestBody(required = true) CreateApparentPowerLimitCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture =
          ApparentPowerLimitBusinessDelegate.getApparentPowerLimitInstance()
              .createApparentPowerLimit(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a ApparentPowerLimit. if no key provided, calls create, otherwise calls save
   *
   * @param ApparentPowerLimit apparentPowerLimit
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(
      @RequestBody(required = true) UpdateApparentPowerLimitCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateApparentPowerLimitCommand
      // -----------------------------------------------
      completableFuture =
          ApparentPowerLimitBusinessDelegate.getApparentPowerLimitInstance()
              .updateApparentPowerLimit(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "ApparentPowerLimitController:update() - successfully update ApparentPowerLimit - "
              + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a ApparentPowerLimit entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID apparentPowerLimitId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteApparentPowerLimitCommand command =
        new DeleteApparentPowerLimitCommand(apparentPowerLimitId);

    try {
      ApparentPowerLimitBusinessDelegate delegate =
          ApparentPowerLimitBusinessDelegate.getApparentPowerLimitInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(
          Level.WARNING,
          "Successfully deleted ApparentPowerLimit with key " + command.getApparentPowerLimitId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Value on ApparentPowerLimit
   *
   * @param command AssignValueToApparentPowerLimitCommand
   */
  @PutMapping("/assignValue")
  public void assignValue(@RequestBody AssignValueToApparentPowerLimitCommand command) {
    try {
      ApparentPowerLimitBusinessDelegate.getApparentPowerLimitInstance().assignValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Value", exc);
    }
  }

  /**
   * unassign Value on ApparentPowerLimit
   *
   * @param command UnAssignValueFromApparentPowerLimitCommand
   */
  @PutMapping("/unAssignValue")
  public void unAssignValue(
      @RequestBody(required = true) UnAssignValueFromApparentPowerLimitCommand command) {
    try {
      ApparentPowerLimitBusinessDelegate.getApparentPowerLimitInstance().unAssignValue(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Value", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected ApparentPowerLimit apparentPowerLimit = null;
  private static final Logger LOGGER =
      Logger.getLogger(ApparentPowerLimitCommandRestController.class.getName());
}
