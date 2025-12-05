import { Piece, getPieceSymbol } from '@/lib/chessEngine';

interface ChessPieceProps {
  piece: Piece;
  isSelected?: boolean;
  isDragging?: boolean;
  size?: 'small' | 'normal';
}

export function ChessPiece({ piece, isSelected, isDragging, size = 'normal' }: ChessPieceProps) {
  const symbol = getPieceSymbol(piece);
  
  const sizeClasses = size === 'small' 
    ? 'text-2xl' 
    : 'text-4xl sm:text-5xl';
  
  return (
    <span
      className={`
        ${sizeClasses}
        select-none
        transition-transform duration-150
        ${piece.color === 'white' ? 'text-white drop-shadow-[0_2px_4px_rgba(0,0,0,0.8)]' : 'text-gray-400 drop-shadow-[0_2px_4px_rgba(0,0,0,0.5)]'}
        ${isSelected ? 'scale-110' : ''}
        ${isDragging ? 'scale-125 cursor-grabbing' : 'cursor-grab'}
      `}
      data-testid={`piece-${piece.color}-${piece.type}`}
    >
      {symbol}
    </span>
  );
}
