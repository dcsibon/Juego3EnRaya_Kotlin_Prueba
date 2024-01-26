
fun clearConsole(num: Int) {
    (1..num).forEach{ _ -> println() }
}

fun printRow(row: IntArray) {
    // Mostrar una fila del tablero
    println("| " + row.joinToString(" | ") { if (it == 0) " " else if (it == 1) "X" else "O" } + " |")
    println("-------------")
}

fun printBoard(board: Array<IntArray>) {
    // Mostrar en consola el tablero con las fichas
    clearConsole(20)
    println("-------------")
    board.forEach { printRow(it) }
}

fun createBoard(size: Int = 3) = Array(size) { IntArray(size) }

fun checkWinner(board: Array<IntArray>): Int {
    // Comprobar si algún jugador ha ganado
    for (i in 0..<3) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0) {
            return board[i][0]
        }
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0) {
            return board[0][i]
        }
    }
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0) {
        return board[0][0]
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0) {
        return board[0][2]
    }
    return 0
}

fun isBoardFull(board: Array<IntArray>): Boolean {
    // Verificar si el tablero está lleno
    return board.all { row -> row.all { it != 0 } }
}

fun getInput(msj: String) : Int {
    print(msj)
    val input = readln()
    var value = 0

    do {
        if (input.isBlank()) {
            return -1 //if returns -1 => Exit Game
        }
        else {
            try {
                value = input.toInt().minus(1)
            }
            catch (e: NumberFormatException) {
                println("**Error** Número inválido. Inténtalo de nuevo.")
            }
        }
    } while (value !in 0..3)

    return value
}

fun placePiece(board: Array<IntArray>, player: Int) {
    var salir = false
    while (!salir) {
        val row = getInput("Elige la fila (1, 2, 3): ")

        val col = getInput("Elige la columna (1, 2, 3): ")

        if (board[row][col] == 0) {
            board[row][col] = player
            salir = true
        } else {
            println("**Error** Movimiento inválido. Inténtalo de nuevo.")
        }
    }
}

fun switchPlayer(player: Int): Int {
    // Cambiar de jugador
    return if (player == 1) 2 else 1
}

fun main() {
    val board = createBoard()
    var currentPlayer = 1

    var winner = 0

    while (winner == 0) {
        printBoard(board)
        placePiece(board, currentPlayer)

        winner = checkWinner(board)

        if (winner != 0) {
            printBoard(board)
            println("¡El jugador $winner ha ganado!")
            break
        } else if (isBoardFull(board)) {
            printBoard(board)
            println("El juego ha terminado en empate.")
            break
        }

        currentPlayer = switchPlayer(currentPlayer)
    }
}
