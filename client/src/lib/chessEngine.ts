export type PieceType = 'king' | 'queen' | 'rook' | 'bishop' | 'knight' | 'pawn';
export type PieceColor = 'white' | 'black';

export interface Piece {
  type: PieceType;
  color: PieceColor;
  hasMoved?: boolean;
}

export interface Square {
  row: number;
  col: number;
}

export interface Move {
  from: Square;
  to: Square;
  piece: Piece;
  captured?: Piece;
  isPromotion?: boolean;
  promotedTo?: PieceType;
  isCastling?: boolean;
  isEnPassant?: boolean;
  notation?: string;
}

export type Board = (Piece | null)[][];

export interface GameState {
  board: Board;
  currentPlayer: PieceColor;
  moveHistory: Move[];
  capturedPieces: { white: Piece[]; black: Piece[] };
  isCheck: boolean;
  isCheckmate: boolean;
  isStalemate: boolean;
  enPassantTarget: Square | null;
  castlingRights: {
    white: { kingSide: boolean; queenSide: boolean };
    black: { kingSide: boolean; queenSide: boolean };
  };
  kingPositions: { white: Square; black: Square };
}

export function createInitialBoard(): Board {
  const board: Board = Array(8).fill(null).map(() => Array(8).fill(null));

  const backRow: PieceType[] = ['rook', 'knight', 'bishop', 'queen', 'king', 'bishop', 'knight', 'rook'];
  
  for (let col = 0; col < 8; col++) {
    board[0][col] = { type: backRow[col], color: 'black' };
    board[1][col] = { type: 'pawn', color: 'black' };
    board[6][col] = { type: 'pawn', color: 'white' };
    board[7][col] = { type: backRow[col], color: 'white' };
  }

  return board;
}

export function createInitialGameState(): GameState {
  return {
    board: createInitialBoard(),
    currentPlayer: 'white',
    moveHistory: [],
    capturedPieces: { white: [], black: [] },
    isCheck: false,
    isCheckmate: false,
    isStalemate: false,
    enPassantTarget: null,
    castlingRights: {
      white: { kingSide: true, queenSide: true },
      black: { kingSide: true, queenSide: true },
    },
    kingPositions: {
      white: { row: 7, col: 4 },
      black: { row: 0, col: 4 },
    },
  };
}

export function squareEquals(a: Square, b: Square): boolean {
  return a.row === b.row && a.col === b.col;
}

export function isInBounds(row: number, col: number): boolean {
  return row >= 0 && row < 8 && col >= 0 && col < 8;
}

export function getPieceAt(board: Board, square: Square): Piece | null {
  return board[square.row][square.col];
}

function getRawMoves(board: Board, from: Square, piece: Piece, enPassantTarget: Square | null): Square[] {
  const moves: Square[] = [];
  const { row, col } = from;
  const direction = piece.color === 'white' ? -1 : 1;

  switch (piece.type) {
    case 'pawn': {
      const nextRow = row + direction;
      if (isInBounds(nextRow, col) && !board[nextRow][col]) {
        moves.push({ row: nextRow, col });
        
        const startRow = piece.color === 'white' ? 6 : 1;
        const doubleRow = row + 2 * direction;
        if (row === startRow && isInBounds(doubleRow, col) && !board[doubleRow][col]) {
          moves.push({ row: doubleRow, col });
        }
      }
      
      for (const dc of [-1, 1]) {
        const captureCol = col + dc;
        if (isInBounds(nextRow, captureCol)) {
          const target = board[nextRow][captureCol];
          if (target && target.color !== piece.color) {
            moves.push({ row: nextRow, col: captureCol });
          }
          
          if (enPassantTarget && enPassantTarget.row === nextRow && enPassantTarget.col === captureCol) {
            moves.push({ row: nextRow, col: captureCol });
          }
        }
      }
      break;
    }

    case 'knight': {
      const knightMoves = [
        [-2, -1], [-2, 1], [-1, -2], [-1, 2],
        [1, -2], [1, 2], [2, -1], [2, 1]
      ];
      for (const [dr, dc] of knightMoves) {
        const newRow = row + dr;
        const newCol = col + dc;
        if (isInBounds(newRow, newCol)) {
          const target = board[newRow][newCol];
          if (!target || target.color !== piece.color) {
            moves.push({ row: newRow, col: newCol });
          }
        }
      }
      break;
    }

    case 'bishop': {
      const directions = [[-1, -1], [-1, 1], [1, -1], [1, 1]];
      for (const [dr, dc] of directions) {
        for (let i = 1; i < 8; i++) {
          const newRow = row + dr * i;
          const newCol = col + dc * i;
          if (!isInBounds(newRow, newCol)) break;
          const target = board[newRow][newCol];
          if (!target) {
            moves.push({ row: newRow, col: newCol });
          } else {
            if (target.color !== piece.color) {
              moves.push({ row: newRow, col: newCol });
            }
            break;
          }
        }
      }
      break;
    }

    case 'rook': {
      const directions = [[-1, 0], [1, 0], [0, -1], [0, 1]];
      for (const [dr, dc] of directions) {
        for (let i = 1; i < 8; i++) {
          const newRow = row + dr * i;
          const newCol = col + dc * i;
          if (!isInBounds(newRow, newCol)) break;
          const target = board[newRow][newCol];
          if (!target) {
            moves.push({ row: newRow, col: newCol });
          } else {
            if (target.color !== piece.color) {
              moves.push({ row: newRow, col: newCol });
            }
            break;
          }
        }
      }
      break;
    }

    case 'queen': {
      const directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1], [0, 1],
        [1, -1], [1, 0], [1, 1]
      ];
      for (const [dr, dc] of directions) {
        for (let i = 1; i < 8; i++) {
          const newRow = row + dr * i;
          const newCol = col + dc * i;
          if (!isInBounds(newRow, newCol)) break;
          const target = board[newRow][newCol];
          if (!target) {
            moves.push({ row: newRow, col: newCol });
          } else {
            if (target.color !== piece.color) {
              moves.push({ row: newRow, col: newCol });
            }
            break;
          }
        }
      }
      break;
    }

    case 'king': {
      const directions = [
        [-1, -1], [-1, 0], [-1, 1],
        [0, -1], [0, 1],
        [1, -1], [1, 0], [1, 1]
      ];
      for (const [dr, dc] of directions) {
        const newRow = row + dr;
        const newCol = col + dc;
        if (isInBounds(newRow, newCol)) {
          const target = board[newRow][newCol];
          if (!target || target.color !== piece.color) {
            moves.push({ row: newRow, col: newCol });
          }
        }
      }
      break;
    }
  }

  return moves;
}

function isSquareAttacked(board: Board, square: Square, attackerColor: PieceColor, enPassantTarget: Square | null): boolean {
  for (let row = 0; row < 8; row++) {
    for (let col = 0; col < 8; col++) {
      const piece = board[row][col];
      if (piece && piece.color === attackerColor) {
        if (piece.type === 'pawn') {
          const direction = piece.color === 'white' ? -1 : 1;
          const attackRow = row + direction;
          if (attackRow === square.row && (col - 1 === square.col || col + 1 === square.col)) {
            return true;
          }
        } else {
          const moves = getRawMoves(board, { row, col }, piece, enPassantTarget);
          if (moves.some(m => squareEquals(m, square))) {
            return true;
          }
        }
      }
    }
  }
  return false;
}

function findKing(board: Board, color: PieceColor): Square | null {
  for (let row = 0; row < 8; row++) {
    for (let col = 0; col < 8; col++) {
      const piece = board[row][col];
      if (piece && piece.type === 'king' && piece.color === color) {
        return { row, col };
      }
    }
  }
  return null;
}

function isKingInCheck(board: Board, kingColor: PieceColor, enPassantTarget: Square | null): boolean {
  const kingPos = findKing(board, kingColor);
  if (!kingPos) return false;
  const attackerColor = kingColor === 'white' ? 'black' : 'white';
  return isSquareAttacked(board, kingPos, attackerColor, enPassantTarget);
}

function simulateMove(board: Board, from: Square, to: Square): Board {
  const newBoard = board.map(row => [...row]);
  const piece = newBoard[from.row][from.col];
  newBoard[to.row][to.col] = piece;
  newBoard[from.row][from.col] = null;
  return newBoard;
}

export function getValidMoves(state: GameState, from: Square): Square[] {
  const piece = getPieceAt(state.board, from);
  if (!piece || piece.color !== state.currentPlayer) return [];

  const rawMoves = getRawMoves(state.board, from, piece, state.enPassantTarget);
  const validMoves: Square[] = [];

  for (const to of rawMoves) {
    let testBoard = simulateMove(state.board, from, to);
    
    if (piece.type === 'pawn' && state.enPassantTarget && squareEquals(to, state.enPassantTarget)) {
      const capturedRow = from.row;
      testBoard[capturedRow][to.col] = null;
    }
    
    if (!isKingInCheck(testBoard, piece.color, null)) {
      validMoves.push(to);
    }
  }

  if (piece.type === 'king' && !piece.hasMoved) {
    const castlingRights = state.castlingRights[piece.color];
    const row = piece.color === 'white' ? 7 : 0;
    
    if (castlingRights.kingSide) {
      const rookPos = { row, col: 7 };
      const rook = getPieceAt(state.board, rookPos);
      if (rook && rook.type === 'rook' && !rook.hasMoved) {
        const pathClear = !state.board[row][5] && !state.board[row][6];
        if (pathClear && !isKingInCheck(state.board, piece.color, state.enPassantTarget)) {
          const opponent = piece.color === 'white' ? 'black' : 'white';
          const path = [{ row, col: 4 }, { row, col: 5 }, { row, col: 6 }];
          const pathSafe = path.every(sq => !isSquareAttacked(state.board, sq, opponent, state.enPassantTarget));
          if (pathSafe) {
            validMoves.push({ row, col: 6 });
          }
        }
      }
    }
    
    if (castlingRights.queenSide) {
      const rookPos = { row, col: 0 };
      const rook = getPieceAt(state.board, rookPos);
      if (rook && rook.type === 'rook' && !rook.hasMoved) {
        const pathClear = !state.board[row][1] && !state.board[row][2] && !state.board[row][3];
        if (pathClear && !isKingInCheck(state.board, piece.color, state.enPassantTarget)) {
          const opponent = piece.color === 'white' ? 'black' : 'white';
          const path = [{ row, col: 2 }, { row, col: 3 }, { row, col: 4 }];
          const pathSafe = path.every(sq => !isSquareAttacked(state.board, sq, opponent, state.enPassantTarget));
          if (pathSafe) {
            validMoves.push({ row, col: 2 });
          }
        }
      }
    }
  }

  return validMoves;
}

function hasAnyValidMoves(state: GameState, color: PieceColor): boolean {
  for (let row = 0; row < 8; row++) {
    for (let col = 0; col < 8; col++) {
      const piece = state.board[row][col];
      if (piece && piece.color === color) {
        const tempState = { ...state, currentPlayer: color };
        const moves = getValidMoves(tempState, { row, col });
        if (moves.length > 0) return true;
      }
    }
  }
  return false;
}

function getMoveNotation(piece: Piece, from: Square, to: Square, captured: boolean, isCheck: boolean, isCheckmate: boolean, isCastling?: 'kingside' | 'queenside', promotedTo?: PieceType): string {
  if (isCastling === 'kingside') return 'O-O';
  if (isCastling === 'queenside') return 'O-O-O';

  const files = 'abcdefgh';
  const ranks = '87654321';
  
  const pieceNotation: Record<PieceType, string> = {
    king: 'K',
    queen: 'Q',
    rook: 'R',
    bishop: 'B',
    knight: 'N',
    pawn: '',
  };

  let notation = pieceNotation[piece.type];
  
  if (piece.type === 'pawn' && captured) {
    notation = files[from.col];
  }
  
  if (captured) notation += 'x';
  notation += files[to.col] + ranks[to.row];
  
  if (promotedTo) {
    notation += '=' + pieceNotation[promotedTo];
  }
  
  if (isCheckmate) notation += '#';
  else if (isCheck) notation += '+';

  return notation;
}

export function makeMove(state: GameState, from: Square, to: Square, promotionPiece?: PieceType): GameState | null {
  const piece = getPieceAt(state.board, from);
  if (!piece || piece.color !== state.currentPlayer) return null;

  const validMoves = getValidMoves(state, from);
  if (!validMoves.some(m => squareEquals(m, to))) return null;

  const newBoard = state.board.map(row => [...row]);
  const captured = newBoard[to.row][to.col];
  let isCastling: 'kingside' | 'queenside' | undefined;
  let isEnPassant = false;

  if (piece.type === 'king' && Math.abs(to.col - from.col) === 2) {
    if (to.col === 6) {
      isCastling = 'kingside';
      newBoard[to.row][5] = newBoard[to.row][7];
      newBoard[to.row][7] = null;
      if (newBoard[to.row][5]) {
        newBoard[to.row][5] = { ...newBoard[to.row][5]!, hasMoved: true };
      }
    } else if (to.col === 2) {
      isCastling = 'queenside';
      newBoard[to.row][3] = newBoard[to.row][0];
      newBoard[to.row][0] = null;
      if (newBoard[to.row][3]) {
        newBoard[to.row][3] = { ...newBoard[to.row][3]!, hasMoved: true };
      }
    }
  }

  let capturedPiece = captured;
  if (piece.type === 'pawn' && state.enPassantTarget && squareEquals(to, state.enPassantTarget)) {
    isEnPassant = true;
    capturedPiece = newBoard[from.row][to.col];
    newBoard[from.row][to.col] = null;
  }

  let movedPiece: Piece = { ...piece, hasMoved: true };
  if (piece.type === 'pawn' && (to.row === 0 || to.row === 7)) {
    movedPiece = { type: promotionPiece || 'queen', color: piece.color, hasMoved: true };
  }

  newBoard[to.row][to.col] = movedPiece;
  newBoard[from.row][from.col] = null;

  let newEnPassantTarget: Square | null = null;
  if (piece.type === 'pawn' && Math.abs(to.row - from.row) === 2) {
    newEnPassantTarget = { row: (from.row + to.row) / 2, col: from.col };
  }

  const newCastlingRights = JSON.parse(JSON.stringify(state.castlingRights));
  if (piece.type === 'king') {
    newCastlingRights[piece.color].kingSide = false;
    newCastlingRights[piece.color].queenSide = false;
  }
  if (piece.type === 'rook') {
    const rookRow = piece.color === 'white' ? 7 : 0;
    if (from.row === rookRow && from.col === 0) newCastlingRights[piece.color].queenSide = false;
    if (from.row === rookRow && from.col === 7) newCastlingRights[piece.color].kingSide = false;
  }
  if (captured?.type === 'rook') {
    const color = captured.color;
    const rookRow = color === 'white' ? 7 : 0;
    if (to.row === rookRow && to.col === 0) newCastlingRights[color].queenSide = false;
    if (to.row === rookRow && to.col === 7) newCastlingRights[color].kingSide = false;
  }

  const newKingPositions = { ...state.kingPositions };
  if (piece.type === 'king') {
    newKingPositions[piece.color] = to;
  }

  const nextPlayer = state.currentPlayer === 'white' ? 'black' : 'white';
  const newCapturedPieces = { ...state.capturedPieces };
  if (capturedPiece) {
    newCapturedPieces[capturedPiece.color] = [...newCapturedPieces[capturedPiece.color], capturedPiece];
  }

  const isCheck = isKingInCheck(newBoard, nextPlayer, newEnPassantTarget);
  
  const tempState: GameState = {
    ...state,
    board: newBoard,
    currentPlayer: nextPlayer,
    enPassantTarget: newEnPassantTarget,
    castlingRights: newCastlingRights,
    kingPositions: newKingPositions,
  };
  
  const hasValidMoves = hasAnyValidMoves(tempState, nextPlayer);
  const isCheckmate = isCheck && !hasValidMoves;
  const isStalemate = !isCheck && !hasValidMoves;

  const notation = getMoveNotation(
    piece,
    from,
    to,
    !!capturedPiece,
    isCheck,
    isCheckmate,
    isCastling,
    piece.type === 'pawn' && (to.row === 0 || to.row === 7) ? (promotionPiece || 'queen') : undefined
  );

  const move: Move = {
    from,
    to,
    piece,
    captured: capturedPiece || undefined,
    isPromotion: piece.type === 'pawn' && (to.row === 0 || to.row === 7),
    promotedTo: piece.type === 'pawn' && (to.row === 0 || to.row === 7) ? (promotionPiece || 'queen') : undefined,
    isCastling: !!isCastling,
    isEnPassant,
    notation,
  };

  return {
    board: newBoard,
    currentPlayer: nextPlayer,
    moveHistory: [...state.moveHistory, move],
    capturedPieces: newCapturedPieces,
    isCheck,
    isCheckmate,
    isStalemate,
    enPassantTarget: newEnPassantTarget,
    castlingRights: newCastlingRights,
    kingPositions: newKingPositions,
  };
}

export function isPawnPromotion(state: GameState, from: Square, to: Square): boolean {
  const piece = getPieceAt(state.board, from);
  if (!piece || piece.type !== 'pawn') return false;
  return (piece.color === 'white' && to.row === 0) || (piece.color === 'black' && to.row === 7);
}

export function getPieceSymbol(piece: Piece): string {
  const symbols: Record<PieceType, { white: string; black: string }> = {
    king: { white: '\u2654', black: '\u265A' },
    queen: { white: '\u2655', black: '\u265B' },
    rook: { white: '\u2656', black: '\u265C' },
    bishop: { white: '\u2657', black: '\u265D' },
    knight: { white: '\u2658', black: '\u265E' },
    pawn: { white: '\u2659', black: '\u265F' },
  };
  return symbols[piece.type][piece.color];
}

export function getPieceValue(type: PieceType): number {
  const values: Record<PieceType, number> = {
    pawn: 1,
    knight: 3,
    bishop: 3,
    rook: 5,
    queen: 9,
    king: 0,
  };
  return values[type];
}
