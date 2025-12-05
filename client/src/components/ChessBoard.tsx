import { useState, useCallback } from 'react';
import { 
  GameState, 
  Square, 
  getValidMoves, 
  makeMove, 
  isPawnPromotion,
  squareEquals,
  PieceType
} from '@/lib/chessEngine';
import { ChessPiece } from './ChessPiece';
import { PromotionModal } from './PromotionModal';

interface ChessBoardProps {
  gameState: GameState;
  onMove: (newState: GameState) => void;
}

export function ChessBoard({ gameState, onMove }: ChessBoardProps) {
  const [selectedSquare, setSelectedSquare] = useState<Square | null>(null);
  const [validMoves, setValidMoves] = useState<Square[]>([]);
  const [pendingPromotion, setPendingPromotion] = useState<{ from: Square; to: Square } | null>(null);
  const [draggedPiece, setDraggedPiece] = useState<Square | null>(null);

  const handleSquareClick = useCallback((row: number, col: number) => {
    const square: Square = { row, col };
    const piece = gameState.board[row][col];

    if (selectedSquare) {
      const isValidMove = validMoves.some(m => squareEquals(m, square));
      
      if (isValidMove) {
        if (isPawnPromotion(gameState, selectedSquare, square)) {
          setPendingPromotion({ from: selectedSquare, to: square });
        } else {
          const newState = makeMove(gameState, selectedSquare, square);
          if (newState) {
            onMove(newState);
          }
        }
        setSelectedSquare(null);
        setValidMoves([]);
        return;
      }
    }

    if (piece && piece.color === gameState.currentPlayer) {
      setSelectedSquare(square);
      setValidMoves(getValidMoves(gameState, square));
    } else {
      setSelectedSquare(null);
      setValidMoves([]);
    }
  }, [gameState, selectedSquare, validMoves, onMove]);

  const handleDragStart = useCallback((row: number, col: number) => {
    const piece = gameState.board[row][col];
    if (piece && piece.color === gameState.currentPlayer) {
      setDraggedPiece({ row, col });
      setSelectedSquare({ row, col });
      setValidMoves(getValidMoves(gameState, { row, col }));
    }
  }, [gameState]);

  const handleDragEnd = useCallback(() => {
    setDraggedPiece(null);
  }, []);

  const handleDrop = useCallback((row: number, col: number) => {
    if (!draggedPiece) return;
    
    const to: Square = { row, col };
    const isValidMove = validMoves.some(m => squareEquals(m, to));
    
    if (isValidMove) {
      if (isPawnPromotion(gameState, draggedPiece, to)) {
        setPendingPromotion({ from: draggedPiece, to });
      } else {
        const newState = makeMove(gameState, draggedPiece, to);
        if (newState) {
          onMove(newState);
        }
      }
    }
    
    setDraggedPiece(null);
    setSelectedSquare(null);
    setValidMoves([]);
  }, [draggedPiece, gameState, validMoves, onMove]);

  const handlePromotion = useCallback((pieceType: PieceType) => {
    if (!pendingPromotion) return;
    
    const newState = makeMove(gameState, pendingPromotion.from, pendingPromotion.to, pieceType);
    if (newState) {
      onMove(newState);
    }
    setPendingPromotion(null);
  }, [gameState, pendingPromotion, onMove]);

  const isSquareSelected = (row: number, col: number) => 
    selectedSquare && selectedSquare.row === row && selectedSquare.col === col;

  const isValidMoveSquare = (row: number, col: number) =>
    validMoves.some(m => m.row === row && m.col === col);

  const isKingInCheck = (row: number, col: number) => {
    const piece = gameState.board[row][col];
    if (!piece || piece.type !== 'king') return false;
    return gameState.isCheck && piece.color === gameState.currentPlayer;
  };

  const hasCaptureTarget = (row: number, col: number) => {
    if (!isValidMoveSquare(row, col)) return false;
    return gameState.board[row][col] !== null;
  };

  const lastMove = gameState.moveHistory[gameState.moveHistory.length - 1];
  const isLastMoveSquare = (row: number, col: number) => {
    if (!lastMove) return false;
    return squareEquals(lastMove.from, { row, col }) || squareEquals(lastMove.to, { row, col });
  };

  return (
    <>
      <div className="relative chess-board-glow rounded-lg p-1 bg-gradient-to-br from-[#4facfe]/20 to-[#f5576c]/20">
        <div 
          className="grid grid-cols-8 rounded-md overflow-hidden"
          data-testid="chess-board"
        >
          {Array.from({ length: 8 }, (_, row) =>
            Array.from({ length: 8 }, (_, col) => {
              const piece = gameState.board[row][col];
              const isLight = (row + col) % 2 === 0;
              const selected = isSquareSelected(row, col);
              const validMove = isValidMoveSquare(row, col);
              const checkSquare = isKingInCheck(row, col);
              const captureMove = hasCaptureTarget(row, col);
              const lastMoveHighlight = isLastMoveSquare(row, col);

              return (
                <div
                  key={`${row}-${col}`}
                  className={`
                    aspect-square flex items-center justify-center relative
                    w-12 h-12 sm:w-14 sm:h-14 md:w-16 md:h-16 lg:w-[70px] lg:h-[70px]
                    transition-all duration-150
                    ${isLight 
                      ? 'bg-white/[0.08]' 
                      : 'bg-white/[0.02]'
                    }
                    ${selected ? 'chess-selected-glow' : ''}
                    ${checkSquare ? 'chess-check-glow' : ''}
                    ${lastMoveHighlight ? 'bg-[#4facfe]/20' : ''}
                    ${validMove && !captureMove ? 'chess-valid-move' : ''}
                    ${captureMove ? 'chess-capture-move' : ''}
                    hover:brightness-125
                    cursor-pointer
                  `}
                  onClick={() => handleSquareClick(row, col)}
                  onDragOver={(e) => e.preventDefault()}
                  onDrop={() => handleDrop(row, col)}
                  data-testid={`square-${String.fromCharCode(97 + col)}${8 - row}`}
                >
                  {col === 0 && (
                    <span className="absolute left-1 top-0.5 text-[10px] text-white/40 font-medium">
                      {8 - row}
                    </span>
                  )}
                  {row === 7 && (
                    <span className="absolute right-1 bottom-0.5 text-[10px] text-white/40 font-medium">
                      {String.fromCharCode(97 + col)}
                    </span>
                  )}
                  {piece && (
                    <div
                      draggable
                      onDragStart={() => handleDragStart(row, col)}
                      onDragEnd={handleDragEnd}
                      className={`
                        ${draggedPiece && squareEquals(draggedPiece, { row, col }) ? 'opacity-50' : ''}
                      `}
                    >
                      <ChessPiece 
                        piece={piece} 
                        isSelected={selected}
                        isDragging={draggedPiece && squareEquals(draggedPiece, { row, col })}
                      />
                    </div>
                  )}
                </div>
              );
            })
          )}
        </div>
      </div>

      {pendingPromotion && (
        <PromotionModal
          color={gameState.currentPlayer}
          onSelect={handlePromotion}
          onCancel={() => setPendingPromotion(null)}
        />
      )}
    </>
  );
}
