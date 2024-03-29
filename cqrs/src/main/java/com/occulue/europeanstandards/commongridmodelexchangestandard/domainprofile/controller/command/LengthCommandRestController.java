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
package com.occulue.europeanstandards.commongridmodelexchangestandard.domainprofile.controller.command;

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
 * Implements Spring Controller command CQRS processing for entity Length.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Length")
public class LengthCommandRestController extends BaseSpringRestController {

  /**
   * Handles create a Length. if not key provided, calls create, otherwise calls save
   *
   * @param Length length
   * @return CompletableFuture<UUID>
   */
  @PostMapping("/create")
  public CompletableFuture<UUID> create(@RequestBody(required = true) CreateLengthCommand command) {
    CompletableFuture<UUID> completableFuture = null;
    try {

      completableFuture = LengthBusinessDelegate.getLengthInstance().createLength(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage(), exc);
    }

    return completableFuture;
  }

  /**
   * Handles updating a Length. if no key provided, calls create, otherwise calls save
   *
   * @param Length length
   * @return CompletableFuture<Void>
   */
  @PutMapping("/update")
  public CompletableFuture<Void> update(@RequestBody(required = true) UpdateLengthCommand command) {
    CompletableFuture<Void> completableFuture = null;
    try {
      // -----------------------------------------------
      // delegate the UpdateLengthCommand
      // -----------------------------------------------
      completableFuture = LengthBusinessDelegate.getLengthInstance().updateLength(command);
      ;
    } catch (Throwable exc) {
      LOGGER.log(
          Level.WARNING,
          "LengthController:update() - successfully update Length - " + exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * Handles deleting a Length entity
   *
   * @param command ${class.getDeleteCommandAlias()}
   * @return CompletableFuture<Void>
   */
  @DeleteMapping("/delete")
  public CompletableFuture<Void> delete(@RequestParam(required = true) UUID lengthId) {
    CompletableFuture<Void> completableFuture = null;
    DeleteLengthCommand command = new DeleteLengthCommand(lengthId);

    try {
      LengthBusinessDelegate delegate = LengthBusinessDelegate.getLengthInstance();

      completableFuture = delegate.delete(command);
      LOGGER.log(Level.WARNING, "Successfully deleted Length with key " + command.getLengthId());
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, exc.getMessage());
    }

    return completableFuture;
  }

  /**
   * save Value on Length
   *
   * @param command AssignValueToLengthCommand
   */
  @PutMapping("/assignValue")
  public void assignValue(@RequestBody AssignValueToLengthCommand command) {
    try {
      LengthBusinessDelegate.getLengthInstance().assignValue(command);
    } catch (Throwable exc) {
      LOGGER.log(Level.WARNING, "Failed to assign Value", exc);
    }
  }

  /**
   * unassign Value on Length
   *
   * @param command UnAssignValueFromLengthCommand
   */
  @PutMapping("/unAssignValue")
  public void unAssignValue(@RequestBody(required = true) UnAssignValueFromLengthCommand command) {
    try {
      LengthBusinessDelegate.getLengthInstance().unAssignValue(command);
    } catch (Exception exc) {
      LOGGER.log(Level.WARNING, "Failed to unassign Value", exc);
    }
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  protected Length length = null;
  private static final Logger LOGGER =
      Logger.getLogger(LengthCommandRestController.class.getName());
}
