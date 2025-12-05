import { useState, useCallback } from 'react';
import { GameState, createInitialGameState } from '@/lib/chessEngine';
import { Particles } from '@/components/Particles';
import { Navbar } from '@/components/Navbar';
import { ChessBoard } from '@/components/ChessBoard';
import { GameStatus } from '@/components/GameStatus';
import { MoveHistory } from '@/components/MoveHistory';
import { CapturedPieces } from '@/components/CapturedPieces';
import { GameOverModal } from '@/components/GameOverModal';
import { RulesModal } from '@/components/RulesModal';

export default function Home() {
  const [gameState, setGameState] = useState<GameState>(createInitialGameState);
  const [showRules, setShowRules] = useState(false);

  const handleNewGame = useCallback(() => {
    setGameState(createInitialGameState());
  }, []);

  const handleMove = useCallback((newState: GameState) => {
    setGameState(newState);
  }, []);

  return (
    <div 
      className="min-h-screen bg-[#0a0a1a] text-white overflow-x-hidden"
      data-testid="home-page"
    >
      <Particles />
      <Navbar onNewGame={handleNewGame} onShowRules={() => setShowRules(true)} />
      
      <main className="relative z-10 pt-20 pb-8 px-4 min-h-screen flex items-center justify-center">
        <div className="max-w-6xl w-full mx-auto">
          <div className="flex flex-col lg:flex-row gap-6 items-center lg:items-start justify-center">
            <div className="hidden lg:block w-52 space-y-4">
              <CapturedPieces capturedPieces={gameState.capturedPieces} />
            </div>
            
            <div className="flex flex-col items-center gap-4">
              <GameStatus gameState={gameState} />
              <ChessBoard gameState={gameState} onMove={handleMove} />
              
              <div className="lg:hidden w-full max-w-md space-y-4">
                <CapturedPieces capturedPieces={gameState.capturedPieces} />
              </div>
            </div>
            
            <div className="w-full lg:w-52">
              <MoveHistory moves={gameState.moveHistory} />
            </div>
          </div>
        </div>
      </main>

      <GameOverModal gameState={gameState} onNewGame={handleNewGame} />
      
      {showRules && <RulesModal onClose={() => setShowRules(false)} />}
    </div>
  );
}
