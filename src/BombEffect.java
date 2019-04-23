public class BombEffect extends Effect {

    private EffectType effectType = EffectType.Bomb;

    public void doEffect(Space s, Board b, int row, int col, BoardButton[][] buttons){

        if(s.getPiece().getType() != Piece.ChessPieceType.KING) {
            s.setPiece(null);
            AudioManager.getInstance().playBoom();
        }
        System.out.println("Landed on bomb");

        if(b.getSpace(row+1, col)!=null && b.getSpace(row+1, col).getPiece()!=null)
            b.getSpace(row+1,col).setPiece(null);
        if(b.getSpace(row, col+1)!=null && b.getSpace(row, col+1).getPiece()!=null)
            b.getSpace(row,col+1).setPiece(null);
        if(b.getSpace(row+1, col+1)!=null && b.getSpace(row+1, col+1).getPiece()!=null)
            b.getSpace(row+1,col+1).setPiece(null);
        if(b.getSpace(row+1, col-1)!=null && b.getSpace(row+1, col-1).getPiece()!=null)
            b.getSpace(row+1,col-1).setPiece(null);
        if(b.getSpace(row-1, col)!=null && b.getSpace(row-1, col).getPiece()!=null)
            b.getSpace(row-1,col).setPiece(null);
        if(b.getSpace(row, col-1)!=null && b.getSpace(row, col-1).getPiece()!=null)
            b.getSpace(row,col-1).setPiece(null);
        if(b.getSpace(row-1, col-1)!=null && b.getSpace(row-1, col-1).getPiece()!=null)
            b.getSpace(row-1,col-1).setPiece(null);
        if(b.getSpace(row-1, col+1)!=null && b.getSpace(row-1, col+1).getPiece()!=null)
            b.getSpace(row-1,col+1).setPiece(null);

        updateUI(buttons, b, row, col);

    }

    public EffectType getType(){
        return effectType;
    }

    public void updateUI(BoardButton[][] buttons, Board b, int row, int col){
        int x = row-1;
        int y = col-1;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        y=col;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        y=col+1;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        x=row;
        y=col-1;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        y=col+1;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        x=row+1;
        y=col-1;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        y=col;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
        y=col+1;
        if(b.getSpace(x,y)!=null){
            buttons[x][y].setNewIcon(b.getSpace(x,y).getPiece());
            buttons[x][y].updateUI();
        }
    }
}
