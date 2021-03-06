package AI.EvaluationAspects.PieceEvaluation;

import AI.EvaluationAspects.AbstractBaseEvaluationAspect;
import BoardElements.Pieces.Piece;
import BoardElements.Pieces.PieceType;

import java.util.HashMap;

public class BasePieceValueBonus extends AbstractBaseEvaluationAspect {


    private final Piece piece;
    public BasePieceValueBonus(Piece piece){
        this.piece = piece;
        aspectCoefficient = 1;
    }


    @Override
    protected int calculateAspectValue() {
        return Piece.basePieceValues.get(piece.getType());
    }

    public static int getBaseValueOf(Piece p){
        return Piece.basePieceValues.get(p.getType());
    }

}
