class Board(rows: Int = 3, columns: Int = 3, val players: Array<Player>) {

    val rows: Int
    val columns: Int

    init {
        require(rows > 0) { "Board rows can't be negative." }
        require(columns > 0) { "Board columns can't be negative." }
        this.rows = rows - 1
        this.columns = columns - 1
    }

    fun getRow(piece: Piece) {
        if (piece != null) {
            return "| "
        }
        println("| " + row.joinToString(" | ") { if (it == 0) " " else if (it == 1) "X" else "O" } + " |")
        println("-------------")
    }

    fun printBoardOk(board: Array<IntArray>) {
        // Mostrar en consola el tablero con las fichas
        clearConsoleOk(15)
        println("-------------")
        board.forEach { printRow(it) }
    }

    override fun toString(): String {
        var piece: Piece?
        var result = "-------------\n"
        for (row in 0..this.rows) {
            result += "| "
            for (col in 0..this.columns) {
                piece = players[0].getPiece(row, col)
                if (piece == null) {
                    piece = players[1].getPiece(row, col)
                }
                result += getRow(piece)
            }
            result += " |\n"
        }
    }

}