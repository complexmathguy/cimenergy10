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
package com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.delegate;

import com.occulue.api.*;
import com.occulue.com.occulue.europeanstandards.commongridmodelexchangestandard.dynamicsprofile.userdefinedmodels.validator.*;
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
 * TurbineLoadControllerUserDefined business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of TurbineLoadControllerUserDefined related services in the case of
 *       a TurbineLoadControllerUserDefined business related service failing.
 *   <li>Exposes a simpler, uniform TurbineLoadControllerUserDefined interface to the business tier,
 *       making it easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill
 *       TurbineLoadControllerUserDefined business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class TurbineLoadControllerUserDefinedBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public TurbineLoadControllerUserDefinedBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * TurbineLoadControllerUserDefined Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return TurbineLoadControllerUserDefinedBusinessDelegate
   */
  public static TurbineLoadControllerUserDefinedBusinessDelegate
      getTurbineLoadControllerUserDefinedInstance() {
    return (new TurbineLoadControllerUserDefinedBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createTurbineLoadControllerUserDefined(
      CreateTurbineLoadControllerUserDefinedCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getTurbineLoadControllerUserDefinedId() == null)
        command.setTurbineLoadControllerUserDefinedId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      TurbineLoadControllerUserDefinedValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateTurbineLoadControllerUserDefinedCommand - by convention the future return
      // value for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateTurbineLoadControllerUserDefinedCommand of TurbineLoadControllerUserDefined is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create TurbineLoadControllerUserDefined - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateTurbineLoadControllerUserDefinedCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateTurbineLoadControllerUserDefined(
      UpdateTurbineLoadControllerUserDefinedCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      TurbineLoadControllerUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateTurbineLoadControllerUserDefinedCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save TurbineLoadControllerUserDefined - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteTurbineLoadControllerUserDefinedCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteTurbineLoadControllerUserDefinedCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      TurbineLoadControllerUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteTurbineLoadControllerUserDefinedCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete TurbineLoadControllerUserDefined using Id = "
              + command.getTurbineLoadControllerUserDefinedId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the TurbineLoadControllerUserDefined via
   * TurbineLoadControllerUserDefinedFetchOneSummary
   *
   * @param summary TurbineLoadControllerUserDefinedFetchOneSummary
   * @return TurbineLoadControllerUserDefinedFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public TurbineLoadControllerUserDefined getTurbineLoadControllerUserDefined(
      TurbineLoadControllerUserDefinedFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "TurbineLoadControllerUserDefinedFetchOneSummary arg cannot be null");

    TurbineLoadControllerUserDefined entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      TurbineLoadControllerUserDefinedValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a TurbineLoadControllerUserDefined
      // --------------------------------------
      CompletableFuture<TurbineLoadControllerUserDefined> futureEntity =
          queryGateway.query(
              new FindTurbineLoadControllerUserDefinedQuery(
                  new LoadTurbineLoadControllerUserDefinedFilter(
                      summary.getTurbineLoadControllerUserDefinedId())),
              ResponseTypes.instanceOf(TurbineLoadControllerUserDefined.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate TurbineLoadControllerUserDefined with id "
              + summary.getTurbineLoadControllerUserDefinedId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all TurbineLoadControllerUserDefineds
   *
   * @return List<TurbineLoadControllerUserDefined>
   * @exception ProcessingException Thrown if any problems
   */
  public List<TurbineLoadControllerUserDefined> getAllTurbineLoadControllerUserDefined()
      throws ProcessingException {
    List<TurbineLoadControllerUserDefined> list = null;

    try {
      CompletableFuture<List<TurbineLoadControllerUserDefined>> futureList =
          queryGateway.query(
              new FindAllTurbineLoadControllerUserDefinedQuery(),
              ResponseTypes.multipleInstancesOf(TurbineLoadControllerUserDefined.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all TurbineLoadControllerUserDefined";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * assign Proprietary on TurbineLoadControllerUserDefined
   *
   * @param command AssignProprietaryToTurbineLoadControllerUserDefinedCommand
   * @exception ProcessingException
   */
  public void assignProprietary(AssignProprietaryToTurbineLoadControllerUserDefinedCommand command)
      throws ProcessingException {

    // --------------------------------------------
    // load the parent
    // --------------------------------------------
    load(command.getTurbineLoadControllerUserDefinedId());

    BooleanProxyBusinessDelegate childDelegate =
        BooleanProxyBusinessDelegate.getBooleanProxyInstance();
    TurbineLoadControllerUserDefinedBusinessDelegate parentDelegate =
        TurbineLoadControllerUserDefinedBusinessDelegate
            .getTurbineLoadControllerUserDefinedInstance();
    UUID childId = command.getAssignment().getBooleanProxyId();
    BooleanProxy child = null;

    try {
      // --------------------------------------
      // best to validate the command now
      // --------------------------------------
      TurbineLoadControllerUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);

    } catch (Throwable exc) {
      final String msg = "Failed to get BooleanProxy using id " + childId;
      LOGGER.log(Level.WARNING, msg);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * unAssign Proprietary on TurbineLoadControllerUserDefined
   *
   * @param command UnAssignProprietaryFromTurbineLoadControllerUserDefinedCommand
   * @exception ProcessingException
   */
  public void unAssignProprietary(
      UnAssignProprietaryFromTurbineLoadControllerUserDefinedCommand command)
      throws ProcessingException {

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      TurbineLoadControllerUserDefinedValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the command
      // --------------------------------------
      commandGateway.sendAndWait(command);
    } catch (Exception exc) {
      final String msg = "Failed to unassign Proprietary on TurbineLoadControllerUserDefined";
      LOGGER.log(Level.WARNING, msg, exc);
      throw new ProcessingException(msg, exc);
    }
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return TurbineLoadControllerUserDefined
   */
  private TurbineLoadControllerUserDefined load(UUID id) throws ProcessingException {
    turbineLoadControllerUserDefined =
        TurbineLoadControllerUserDefinedBusinessDelegate
            .getTurbineLoadControllerUserDefinedInstance()
            .getTurbineLoadControllerUserDefined(
                new TurbineLoadControllerUserDefinedFetchOneSummary(id));
    return turbineLoadControllerUserDefined;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private TurbineLoadControllerUserDefined turbineLoadControllerUserDefined = null;
  private static final Logger LOGGER =
      Logger.getLogger(TurbineLoadControllerUserDefinedBusinessDelegate.class.getName());
}
