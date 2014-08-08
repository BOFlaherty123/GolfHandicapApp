package main.java.co.uk.myhandicap.validation.validator;

import main.java.co.uk.myhandicap.validation.annotation.HoleValid;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hole Validation
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 14/07/14
 * @project MyHandicapApp
 */
public class HoleValidator implements ConstraintValidator<HoleValid, Object> {

    private String holePar;
    private String holeScore;
    private String holeSSI;
    private String holeYards;

    @Override
    public void initialize(final HoleValid constraintAnnotation) {
        holePar = constraintAnnotation.holePar();
        holeScore = constraintAnnotation.holeScore();
        holeSSI = constraintAnnotation.holeSSI();
        holeYards = constraintAnnotation.holeYards();
    }

    @Override
    public boolean isValid(final Object holeValues, ConstraintValidatorContext constraintValidatorContext) {

        try {

            String par = getUserInputProperty(holeValues, holePar);
            String score = getUserInputProperty(holeValues, holeScore);
            String ssi = getUserInputProperty(holeValues, holeSSI);
            String yards = getUserInputProperty(holeValues, holeYards);

            if(isHoleFieldNotEmpty(par, score, ssi, yards)) {

                List<String> formVals = new ArrayList<>(
                    Arrays.asList(par, score, ssi, yards)
                );

                for(String value : formVals) {
                    if(value == null) {
                        return false;
                     }
                }

            }

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean isHoleFieldNotEmpty(String par, String score, String ssi, String yards) {
        return !par.isEmpty() || !score.isEmpty() || !ssi.isEmpty() || !yards.isEmpty();
    }

    private String getUserInputProperty(Object holeValues, String property) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return BeanUtils.getProperty(holeValues, property).trim();

    }

}