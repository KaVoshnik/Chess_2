import { GameState } from '@/lib/chessEngine';
import { Button } from '@/components/ui/button';
import { Trophy, Swords } from 'lucide-react';

interface GameOverModalProps {
  gameState: GameState;
  onNewGame: () => void;
}

export function GameOverModal({ gameState, onNewGame }: GameOverModalProps) {
  const { isCheckmate, isStalemate, currentPlayer } = gameState;
  
  if (!isCheckmate && !isStalemate) return null;

  const winner = isCheckmate ? (currentPlayer === 'white' ? 'Black' : 'White') : null;
  const isDraw = isStalemate;

  return (
    <div 
      className="fixed inset-0 z-50 flex items-center justify-center bg-black/80 backdrop-blur-md"
      data-testid="game-over-modal"
    >
      <div className="relative bg-gradient-to-br from-[#0a0a1a] to-[#151530] border border-white/10 rounded-2xl p-8 shadow-2xl max-w-sm w-full mx-4 overflow-hidden">
        <div className="absolute inset-0 opacity-20">
          <div className="absolute top-0 left-0 w-40 h-40 bg-[#4facfe] rounded-full blur-[80px]" />
          <div className="absolute bottom-0 right-0 w-40 h-40 bg-[#f5576c] rounded-full blur-[80px]" />
        </div>
        
        <div className="relative z-10 flex flex-col items-center gap-6">
          <div className={`
            w-20 h-20 rounded-full flex items-center justify-center
            ${isDraw 
              ? 'bg-yellow-500/20 border-2 border-yellow-500/50' 
              : 'chess-gradient-blue shadow-[0_0_30px_rgba(79,172,254,0.5)]'
            }
          `}>
            {isDraw ? (
              <Swords className="w-10 h-10 text-yellow-500" />
            ) : (
              <Trophy className="w-10 h-10 text-white" />
            )}
          </div>

          <div className="text-center">
            <h2 className="text-3xl font-bold mb-2">
              {isDraw ? (
                <span className="text-yellow-500">Stalemate!</span>
              ) : (
                <span className={winner === 'White' ? 'chess-text-blue' : 'chess-text-red'}>
                  {winner} Wins!
                </span>
              )}
            </h2>
            <p className="text-white/60">
              {isDraw 
                ? "The game ended in a draw" 
                : `${winner} delivered checkmate`
              }
            </p>
          </div>

          <div className="flex flex-col gap-3 w-full">
            <Button
              onClick={onNewGame}
              className="w-full chess-gradient-blue text-gray-900 font-semibold hover:opacity-90 transition-opacity"
              data-testid="new-game-modal-button"
            >
              Play Again
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
