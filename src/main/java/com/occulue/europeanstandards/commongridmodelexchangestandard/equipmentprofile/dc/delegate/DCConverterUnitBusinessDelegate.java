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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.dc.delegate;

import com.occulue.api.*;
import com.occulue.com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.dc.validator.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;

/**
 * DCConverterUnit business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of DCConverterUnit related services in the case of a
 *       DCConverterUnit business related service failing.
 *   <li>Exposes a simpler, uniform DCConverterUnit interface to the business tier, making it easy
 *       for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill DCConverterUnit business
 *       related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class DCConverterUnitBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public DCConverterUnitBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * DCConverterUnit Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return DCConverterUnitBusinessDelegate
   */
  public static DCConverterUnitBusinessDelegate getDCConverterUnitInstance() {
    return (new DCConverterUnitBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createDCConverterUnit(CreateDCConverterUnitCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getDCConverterUnitId() == null) command.setDCConverterUnitId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      DCConverterUnitValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateDCConverterUnitCommand - by convention the future return value for a create
      // command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateDCConverterUnitCommand of DCConverterUnit is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create DCConverterUnit - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateDCConverterUnitCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateDCConverterUnit(UpdateDCConverterUnitCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      DCConverterUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateDCConverterUnitCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save DCConverterUnit - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteDCConverterUnitCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteDCConverterUnitCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      DCConverterUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteDCConverterUnitCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete DCConverterUnit using Id = " + command.getDCConverterUnitId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the DCConverterUnit via DCConverterUnitFetchOneSummary
   *
   * @param summary DCConverterUnitFetchOneSummary
   * @return DCConverterUnitFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public DCConverterUnit getDCConverterUnit(DCConverterUnitFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException("DCConverterUnitFetchOneSummary arg cannot be null");

    DCConverterUnit entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      DCConverterUnitValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a DCConverterUnit
      // --------------------------------------
      CompletableFuture<DCConverterUnit> futureEntity =
          queryGateway.query(
              new FindDCConverterUnitQuery(
                  new LoadDCConverterUnitFilter(summary.getDCConverterUnitId())),
              ResponseTypes.instanceOf(DCConverterUnit.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate DCConverterUnit with id " + summary.getDCConverterUnitId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all DCConverterUnits
   *
   * @return List<DCConverterUnit>
   * @exception ProcessingException Thrown if any problems
   */
  public List<DCConverterUnit> getAllDCConverterUnit() throws ProcessingException {
    List<DCConverterUnit> list = null;

    try {
      CompletableFuture<List<DCConverterUnit>> futureList =
          queryGateway.query(
              new FindAllDCConverterUnitQuery(),
              ResponseTypes.multipleInstancesOf(DCConverterUnit.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all DCConverterUnit";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * add DCConverterUnit to DCConverterUnit
   *
   * @param command AssignDCConverterUnitToDCConverterUnitCommand
   * @exception ProcessingException
   */
  public void addToDCConverterUnit(AssignDCConverterUnitToDCConverterUnitCommand command)
      throws ProcessingException {

    // -------------------------------------------
    // load the parent
    // -------------------------------------------
    load(command.getDCConverterUnitId());

    DCConverterUnitBusinessDelegate childDelegate =
        DCConverterUnitBusinessDelegate.getDCConverterUnitInstance();
    DCConverterUnitBusinessDelegate parentDelegate =
        DCConverterUnitBusinessDelegate.getDCConverterUnitInstance();
    UUID childId = command.getAddTo().getDCConverterUnitId();

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      DCConverterUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to add a DCConverterUnit as DCConverterUnit to DCConverterUnit";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * remove DCConverterUnit from DCConverterUnit
   *
   * @param command RemoveDCConverterUnitFromDCConverterUnitCommand
   * @exception ProcessingException
   */
  public void removeFromDCConverterUnit(RemoveDCConverterUnitFromDCConverterUnitCommand command)
      throws ProcessingException {

    DCConverterUnitBusinessDelegate childDelegate =
        DCConverterUnitBusinessDelegate.getDCConverterUnitInstance();
    UUID childId = command.getRemoveFrom().getDCConverterUnitId();

    try {

      // --------------------------------------
      // validate the command
      // --------------------------------------
      DCConverterUnitValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Exception exc) {
      final String msg = "Failed to remove child using Id " + childId;
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return DCConverterUnit
   */
  private DCConverterUnit load(UUID id) throws ProcessingException {
    dCConverterUnit =
        DCConverterUnitBusinessDelegate.getDCConverterUnitInstance()
            .getDCConverterUnit(new DCConverterUnitFetchOneSummary(id));
    return dCConverterUnit;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private DCConverterUnit dCConverterUnit = null;
  private static final Logger LOGGER =
      Logger.getLogger(DCConverterUnitBusinessDelegate.class.getName());
}
