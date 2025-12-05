import { GameState, PieceColor } from '@/lib/chessEngine';
import { Badge } from '@/components/ui/badge';

interface GameStatusProps {
  gameState: GameState;
}

export function GameStatus({ gameState }: GameStatusProps) {
  const { currentPlayer, isCheck, isCheckmate, isStalemate } = gameState;
  
  const getStatusMessage = () => {
    if (isCheckmate) {
      const winner = currentPlayer === 'white' ? 'Black' : 'White';
      return `Checkmate! ${winner} wins!`;
    }
    if (isStalemate) return 'Stalemate! Draw!';
    if (isCheck) return 'Check!';
    return `${currentPlayer === 'white' ? 'White' : 'Black'}'s turn`;
  };

  const getStatusClass = () => {
    if (isCheckmate) return 'chess-gradient-red';
    if (isStalemate) return 'bg-yellow-500/20 border-yellow-500/50';
    if (isCheck) return 'chess-gradient-red animate-pulse';
    return currentPlayer === 'white' 
      ? 'chess-gradient-blue' 
      : 'chess-gradient-red';
  };

  return (
    <div 
      className="flex flex-col items-center gap-3"
      data-testid="game-status"
    >
      <div className="flex items-center gap-4">
        <PlayerIndicator color="white" isActive={currentPlayer === 'white' && !isCheckmate && !isStalemate} />
        <div className="flex flex-col items-center">
          <Badge 
            className={`
              ${getStatusClass()}
              text-white font-semibold px-4 py-1.5 text-sm
              border-0 shadow-lg
            `}
            data-testid="status-badge"
          >
            {getStatusMessage()}
          </Badge>
        </div>
        <PlayerIndicator color="black" isActive={currentPlayer === 'black' && !isCheckmate && !isStalemate} />
      </div>
    </div>
  );
}

function PlayerIndicator({ color, isActive }: { color: PieceColor; isActive: boolean }) {
  return (
    <div 
      className={`
        w-10 h-10 rounded-full flex items-center justify-center
        transition-all duration-300
        ${color === 'white' 
          ? 'bg-white text-gray-900' 
          : 'bg-gray-700 text-white'
        }
        ${isActive 
          ? color === 'white' 
            ? 'ring-2 ring-[#4facfe] shadow-[0_0_20px_rgba(79,172,254,0.5)]' 
            : 'ring-2 ring-[#f5576c] shadow-[0_0_20px_rgba(245,87,108,0.5)]'
          : 'opacity-60'
        }
      `}
      data-testid={`player-indicator-${color}`}
    >
      <span className="text-lg font-bold">{color === 'white' ? '\u2654' : '\u265A'}</span>
    </div>
  );
}
