package BoardElements.Pieces;

import BoardElements.*;
import ChessAbstracts.Check;

import java.util.HashSet;

public class Knight extends Piece {
    public Knight(PieceColor PiececCol, int posCol, int posRow, Side side) {
        super(PieceType.KNIGHT, PiececCol,posCol,posRow,side);

    }


    @Override
    public double calculateRelativeValue() {
        int nPawnsInGame = ChessBoard.board.getNumberOfPawnsInGame();
        HashSet<Square> mobilitySquares = calculateControlledSquares();
        HashSet<Square> pawnControlledSquaresByEnemy = mySide.getOpponent().getPawnControlledSquares();

        mobilitySquares.removeIf(pawnControlledSquaresByEnemy::contains);

        int mobility = mobilitySquares.size();


        relativeValue =  - 1.0/16 * nPawnsInGame + 0.2 * mobility + pieceSquareTableDB.getTableValue(this);

        return  relativeValue;
    }

    protected HashSet<Square> calculateControlledSquares() {
        HashSet<Square> controlledSquares = new HashSet<>();

        for(int absRowOffset = 1; absRowOffset <= 2; absRowOffset++) {
            int absColOffset = (absRowOffset == 1 ? 2 : 1);
            for (int colSign = -1; colSign <= 1; colSign += 2) {
                for (int rowSign = -1; rowSign <= 1; rowSign += 2) {
                    int rowOffset = absRowOffset * rowSign;
                    int colOffset = absColOffset * colSign;

                    int candidateCol = getCol() + colOffset;
                    int candidateRow = getRow() + rowOffset;

                    if (isValidSquare(candidateCol,candidateRow)) {
                        Square candidateSquare = ChessBoard.board.getSquareAt(candidateCol, candidateRow);

                        controlledSquares.add(candidateSquare);
                        if( isTheEnemyKingForMe(this,candidateSquare.getPieceOnThisSquare())){
                            King enemyKing = (King) candidateSquare.getPieceOnThisSquare();
                            enemyKing.addCheck(new Check(this,new HashSet<>())); //knight check cannot be blocked, empty set
                        }

                    }
                }
            }
        }
        return controlledSquares;

    }
}