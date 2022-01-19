package AI.EvaluationAspects.PieceEvaluation;

import AI.EvaluationAspects.AbstractBaseEvaluationAspect;
import BoardElements.ChessBoard;
import BoardElements.Pieces.King;
import BoardElements.Pieces.PieceType;
import BoardElements.Square;

public class PawnShield extends AbstractBaseEvaluationAspect {

    King king;
    public PawnShield(King king){
        this.king = king;
        aspectCoefficient = 30;
    }

    @Override
    protected int calculateAspectValue() {

        int rowAheadIdx = king.getRow() + king.getRowIncrementTowardsOpponent();
        int pawnShield = 0;

        for(int i = -1; i <= 1 ; i++) {
            int colToCheck = king.getCol() + i;
            if (ChessBoard.board.checkIfCoordsAreOnTheBoard(colToCheck,rowAheadIdx)) {
                Square t1 = ChessBoard.board.getSquareAt(king.getCol(), rowAheadIdx);
                if( ! t1.isEmpty() && t1.getPieceOnThisSquare().getType() == PieceType.PAWN){
                    pawnShield++;
                }
            }
        }
        return pawnShield;

    }

}
