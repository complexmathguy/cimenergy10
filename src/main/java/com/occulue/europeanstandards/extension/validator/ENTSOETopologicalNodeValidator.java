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
package com.occulue.europeanstandards.extension.validator;

import com.occulue.api.*;
import org.springframework.util.Assert;

public class ENTSOETopologicalNodeValidator {

  /** default constructor */
  protected ENTSOETopologicalNodeValidator() {}

  /** factory method */
  public static ENTSOETopologicalNodeValidator getInstance() {
    return new ENTSOETopologicalNodeValidator();
  }

  /** handles creation validation for a ENTSOETopologicalNode */
  public void validate(CreateENTSOETopologicalNodeCommand eNTSOETopologicalNode) throws Exception {
    Assert.notNull(eNTSOETopologicalNode, "CreateENTSOETopologicalNodeCommand should not be null");
    //		Assert.isNull( eNTSOETopologicalNode.getENTSOETopologicalNodeId(),
    // "CreateENTSOETopologicalNodeCommand identifier should be null" );
  }

  /** handles update validation for a ENTSOETopologicalNode */
  public void validate(UpdateENTSOETopologicalNodeCommand eNTSOETopologicalNode) throws Exception {
    Assert.notNull(eNTSOETopologicalNode, "UpdateENTSOETopologicalNodeCommand should not be null");
    Assert.notNull(
        eNTSOETopologicalNode.getENTSOETopologicalNodeId(),
        "UpdateENTSOETopologicalNodeCommand identifier should not be null");
  }

  /** handles delete validation for a ENTSOETopologicalNode */
  public void validate(DeleteENTSOETopologicalNodeCommand eNTSOETopologicalNode) throws Exception {
    Assert.notNull(eNTSOETopologicalNode, "{commandAlias} should not be null");
    Assert.notNull(
        eNTSOETopologicalNode.getENTSOETopologicalNodeId(),
        "DeleteENTSOETopologicalNodeCommand identifier should not be null");
  }

  /** handles fetchOne validation for a ENTSOETopologicalNode */
  public void validate(ENTSOETopologicalNodeFetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "ENTSOETopologicalNodeFetchOneSummary should not be null");
    Assert.notNull(
        summary.getENTSOETopologicalNodeId(),
        "ENTSOETopologicalNodeFetchOneSummary identifier should not be null");
  }
}
