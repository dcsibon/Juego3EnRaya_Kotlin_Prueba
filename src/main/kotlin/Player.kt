class Player(val name: String, val nickName: String, val pieceSymbol: String) {

    val pieces: MutableList<Piece> = mutableListOf()

    init {
        require(name.isNotBlank()) { "Name player can't be empty." }
        require(nickName.isNotBlank()) { "Nickname player can't be empty." }
        require(pieceSymbol.isNotBlank()) { "Piece symbol player can't be empty." }
    }

    fun findPiece(row: Int, col: Int): Boolean {
        return (pieces.find { it.row == row && it.column == col } != null)
    }

    fun getPiece(row: Int, col: Int): Piece? {
        return pieces.find { it.row == row && it.column == col }
    }

}