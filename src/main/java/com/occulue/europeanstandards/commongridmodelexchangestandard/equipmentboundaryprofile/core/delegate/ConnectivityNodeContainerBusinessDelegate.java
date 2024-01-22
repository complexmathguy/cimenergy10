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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.core.delegate;

import com.occulue.api.*;
import com.occulue.com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentboundaryprofile.core.validator.*;
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
 * ConnectivityNodeContainer business delegate class.
 *
 * <p>This class implements the Business Delegate design pattern for the purpose of:
 *
 * <ol>
 *   <li>Reducing coupling between the business tier and a client of the business tier by hiding all
 *       business-tier implementation details
 *   <li>Improving the available of ConnectivityNodeContainer related services in the case of a
 *       ConnectivityNodeContainer business related service failing.
 *   <li>Exposes a simpler, uniform ConnectivityNodeContainer interface to the business tier, making
 *       it easy for clients to consume a simple Java object.
 *   <li>Hides the communication protocol that may be required to fulfill ConnectivityNodeContainer
 *       business related services.
 * </ol>
 *
 * <p>
 *
 * @author your_name_here
 */
public class ConnectivityNodeContainerBusinessDelegate extends BaseBusinessDelegate {
  // ************************************************************************
  // Public Methods
  // ************************************************************************
  /** Default Constructor */
  public ConnectivityNodeContainerBusinessDelegate() {
    queryGateway = applicationContext.getBean(QueryGateway.class);
    commandGateway = applicationContext.getBean(CommandGateway.class);
    queryUpdateEmitter = applicationContext.getBean(QueryUpdateEmitter.class);
  }

  /**
   * ConnectivityNodeContainer Business Delegate Factory Method
   *
   * <p>All methods are expected to be self-sufficient.
   *
   * @return ConnectivityNodeContainerBusinessDelegate
   */
  public static ConnectivityNodeContainerBusinessDelegate getConnectivityNodeContainerInstance() {
    return (new ConnectivityNodeContainerBusinessDelegate());
  }

  /**
   * Creates the provided command.
   *
   * @param command ${class.getCreateCommandAlias()}
   * @exception ProcessingException
   * @exception IllegalArgumentException
   * @return CompletableFuture<UUID>
   */
  public CompletableFuture<UUID> createConnectivityNodeContainer(
      CreateConnectivityNodeContainerCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<UUID> completableFuture = null;

    try {
      // --------------------------------------
      // assign identity now if none
      // --------------------------------------
      if (command.getConnectivityNodeContainerId() == null)
        command.setConnectivityNodeContainerId(UUID.randomUUID());

      // --------------------------------------
      // validate the command
      // --------------------------------------
      ConnectivityNodeContainerValidator.getInstance().validate(command);

      // ---------------------------------------
      // issue the CreateConnectivityNodeContainerCommand - by convention the future return value
      // for a create command
      // that is handled by the constructor of an aggregate will return the UUID
      // ---------------------------------------
      completableFuture = commandGateway.send(command);

      LOGGER.log(
          Level.INFO,
          "return from Command Gateway for CreateConnectivityNodeContainerCommand of ConnectivityNodeContainer is "
              + command);

    } catch (Exception exc) {
      final String errMsg = "Unable to create ConnectivityNodeContainer - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Update the provided command.
   *
   * @param command UpdateConnectivityNodeContainerCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   * @exception IllegalArgumentException
   */
  public CompletableFuture<Void> updateConnectivityNodeContainer(
      UpdateConnectivityNodeContainerCommand command)
      throws ProcessingException, IllegalArgumentException {
    CompletableFuture<Void> completableFuture = null;

    try {

      // --------------------------------------
      // validate
      // --------------------------------------
      ConnectivityNodeContainerValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the UpdateConnectivityNodeContainerCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg = "Unable to save ConnectivityNodeContainer - " + exc;
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    }

    return completableFuture;
  }

  /**
   * Deletes the associatied value object
   *
   * @param command DeleteConnectivityNodeContainerCommand
   * @return CompletableFuture<Void>
   * @exception ProcessingException
   */
  public CompletableFuture<Void> delete(DeleteConnectivityNodeContainerCommand command)
      throws ProcessingException, IllegalArgumentException {

    CompletableFuture<Void> completableFuture = null;

    try {
      // --------------------------------------
      // validate the command
      // --------------------------------------
      ConnectivityNodeContainerValidator.getInstance().validate(command);

      // --------------------------------------
      // issue the DeleteConnectivityNodeContainerCommand and return right away
      // --------------------------------------
      completableFuture = commandGateway.send(command);
    } catch (Exception exc) {
      final String errMsg =
          "Unable to delete ConnectivityNodeContainer using Id = "
              + command.getConnectivityNodeContainerId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return completableFuture;
  }

  /**
   * Method to retrieve the ConnectivityNodeContainer via ConnectivityNodeContainerFetchOneSummary
   *
   * @param summary ConnectivityNodeContainerFetchOneSummary
   * @return ConnectivityNodeContainerFetchOneResponse
   * @exception ProcessingException - Thrown if processing any related problems
   * @exception IllegalArgumentException
   */
  public ConnectivityNodeContainer getConnectivityNodeContainer(
      ConnectivityNodeContainerFetchOneSummary summary)
      throws ProcessingException, IllegalArgumentException {

    if (summary == null)
      throw new IllegalArgumentException(
          "ConnectivityNodeContainerFetchOneSummary arg cannot be null");

    ConnectivityNodeContainer entity = null;

    try {
      // --------------------------------------
      // validate the fetch one summary
      // --------------------------------------
      ConnectivityNodeContainerValidator.getInstance().validate(summary);

      // --------------------------------------
      // use queryGateway to send request to Find a ConnectivityNodeContainer
      // --------------------------------------
      CompletableFuture<ConnectivityNodeContainer> futureEntity =
          queryGateway.query(
              new FindConnectivityNodeContainerQuery(
                  new LoadConnectivityNodeContainerFilter(
                      summary.getConnectivityNodeContainerId())),
              ResponseTypes.instanceOf(ConnectivityNodeContainer.class));

      entity = futureEntity.get();
    } catch (Exception exc) {
      final String errMsg =
          "Unable to locate ConnectivityNodeContainer with id "
              + summary.getConnectivityNodeContainerId();
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return entity;
  }

  /**
   * Method to retrieve a collection of all ConnectivityNodeContainers
   *
   * @return List<ConnectivityNodeContainer>
   * @exception ProcessingException Thrown if any problems
   */
  public List<ConnectivityNodeContainer> getAllConnectivityNodeContainer()
      throws ProcessingException {
    List<ConnectivityNodeContainer> list = null;

    try {
      CompletableFuture<List<ConnectivityNodeContainer>> futureList =
          queryGateway.query(
              new FindAllConnectivityNodeContainerQuery(),
              ResponseTypes.multipleInstancesOf(ConnectivityNodeContainer.class));

      list = futureList.get();
    } catch (Exception exc) {
      String errMsg = "Failed to get all ConnectivityNodeContainer";
      LOGGER.log(Level.WARNING, errMsg, exc);
      throw new ProcessingException(errMsg, exc);
    } finally {
    }

    return list;
  }

  /**
   * Internal helper method to load the root
   *
   * @param id UUID
   * @return ConnectivityNodeContainer
   */
  private ConnectivityNodeContainer load(UUID id) throws ProcessingException {
    connectivityNodeContainer =
        ConnectivityNodeContainerBusinessDelegate.getConnectivityNodeContainerInstance()
            .getConnectivityNodeContainer(new ConnectivityNodeContainerFetchOneSummary(id));
    return connectivityNodeContainer;
  }

  // ************************************************************************
  // Attributes
  // ************************************************************************
  private final QueryGateway queryGateway;
  private final CommandGateway commandGateway;
  private final QueryUpdateEmitter queryUpdateEmitter;
  private ConnectivityNodeContainer connectivityNodeContainer = null;
  private static final Logger LOGGER =
      Logger.getLogger(ConnectivityNodeContainerBusinessDelegate.class.getName());
}
