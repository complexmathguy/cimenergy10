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
package com.occulue.europeanstandards.commongridmodelexchangestandard.equipmentprofile.equivalents.validator;

import com.occulue.api.*;
import org.springframework.util.Assert;

public class EquivalentNetworkValidator {

  /** default constructor */
  protected EquivalentNetworkValidator() {}

  /** factory method */
  public static EquivalentNetworkValidator getInstance() {
    return new EquivalentNetworkValidator();
  }

  /** handles creation validation for a EquivalentNetwork */
  public void validate(CreateEquivalentNetworkCommand equivalentNetwork) throws Exception {
    Assert.notNull(equivalentNetwork, "CreateEquivalentNetworkCommand should not be null");
    //		Assert.isNull( equivalentNetwork.getEquivalentNetworkId(), "CreateEquivalentNetworkCommand
    // identifier should be null" );
  }

  /** handles update validation for a EquivalentNetwork */
  public void validate(UpdateEquivalentNetworkCommand equivalentNetwork) throws Exception {
    Assert.notNull(equivalentNetwork, "UpdateEquivalentNetworkCommand should not be null");
    Assert.notNull(
        equivalentNetwork.getEquivalentNetworkId(),
        "UpdateEquivalentNetworkCommand identifier should not be null");
  }

  /** handles delete validation for a EquivalentNetwork */
  public void validate(DeleteEquivalentNetworkCommand equivalentNetwork) throws Exception {
    Assert.notNull(equivalentNetwork, "{commandAlias} should not be null");
    Assert.notNull(
        equivalentNetwork.getEquivalentNetworkId(),
        "DeleteEquivalentNetworkCommand identifier should not be null");
  }

  /** handles fetchOne validation for a EquivalentNetwork */
  public void validate(EquivalentNetworkFetchOneSummary summary) throws Exception {
    Assert.notNull(summary, "EquivalentNetworkFetchOneSummary should not be null");
    Assert.notNull(
        summary.getEquivalentNetworkId(),
        "EquivalentNetworkFetchOneSummary identifier should not be null");
  }
}
