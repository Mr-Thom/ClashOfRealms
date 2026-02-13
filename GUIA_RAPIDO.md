# Guia Rápido de Referência - Clash of Realms

## 🎮 Comandos do Menu

| Tecla | Ação | Requisitos |
|-------|------|------------|
| `0` | Sair | - |
| `1` | Criar Guerreiro | Nome, Idade, Altura |
| `2` | Criar Arqueiro | Nome, Idade, Altura |
| `3` | Criar Mago | Nome, Idade, Altura |
| `4` | Remover Herói | Arena não vazia |
| `5` | Listar Heróis | - |
| `6` | Ordenar Heróis | Arena não vazia |
| `7` | Aniversário | Arena não vazia |
| `8` | Usar Habilidade | ≥ 2 heróis |

---

## ⚔️ Estatísticas das Classes

### Guerreiro

| Atributo | Inicial | Por Evolução | Máximo |
|----------|---------|--------------|---------|
| Dano | 30 | +1 a +5 | 70 |
| Vida | 100% | - | 100% |
| Ataques/Turno | 1 | - | 1 |

**Habilidade:** Ataque físico direto
**Evolui ao:** Matar inimigo
**Bônus por ataque:** +1 dano (se não matar)

### Arqueiro

| Atributo | Inicial | Por Evolução | Máximo |
|----------|---------|--------------|---------|
| Dano | 40 | +1 (níveis ímpares) | 50 |
| Flechas | 1 | +1 (níveis 3 e 5) | ∞ |
| Vida | 100% | - | 100% |

**Habilidade:** Múltiplos ataques aleatórios
**Evolui ao:** Matar inimigo
**Dano:** Aleatório entre 0 e valor máximo

### Mago

| Atributo | Inicial | Por Evolução | Máximo |
|----------|---------|--------------|---------|
| Cura | 20 | +5 | 50 |
| Vida | 100% | - | 100% |

**Habilidade:** Cura ou ressurreição
**Evolui ao:** Reviver aliado
**Limitações:** Só revive uma vez por herói

---

## 📊 Comparação de Classes

| Aspecto | Guerreiro | Arqueiro | Mago |
|---------|-----------|----------|------|
| **Tipo** | Tank/DPS | DPS | Suporte |
| **Dano Total/Turno** | 30-70 | 0-150 (3x50) | 0 |
| **Consistência** | ⭐⭐⭐⭐⭐ | ⭐⭐ | N/A |
| **Burst Damage** | ⭐⭐ | ⭐⭐⭐⭐⭐ | N/A |
| **Suporte** | ❌ | ❌ | ⭐⭐⭐⭐⭐ |
| **Revive** | ❌ | ❌ | ✅ (1x) |
| **Solo Viável** | ✅ | ✅ | ❌ |
| **Evolução** | Rápida | Média | Rápida |

---

## 🎯 Estados do Herói

| Estado | Condição | Pode Atacar | Pode Receber Habilidade | Pode Reviver |
|--------|----------|-------------|-------------------------|--------------|
| **VIVO** | morto=false | ✅ | ✅ | N/A |
| **ATORDOADO** | morto=true, revivido=false | ❌ | ✅ (cura=0) | ✅ |
| **MORTO** | morto=true, revivido=true | ❌ | ✅ (cura=0) | ❌ |

---

## 🔄 Fluxo de Combate

### Ataque do Guerreiro
```
1. Verificar se atacante está vivo
2. Verificar se alvo não é ele mesmo
3. Verificar se alvo está vivo
4. Calcular se mata (vida - dano ≤ 0)
   ├─ SIM: Aplicar dano = vida, marcar morto, evoluir, histórico
   └─ NÃO: Aplicar dano completo, dano++
```

### Ataque do Arqueiro
```
PARA cada flecha de 1 até total:
   1. Calcular dano aleatório (0 a max)
   2. Verificar se mata
      ├─ SIM: Aplicar, marcar morto, evoluir, histórico, PARAR
      └─ NÃO: Aplicar dano, continuar próxima flecha
```

### Cura/Revive do Mago
```
1. Verificar se mago está vivo
2. Verificar estado do alvo:
   ├─ ATORDOADO: Reviver + curar + evoluir + histórico
   ├─ MORTO: Não fazer nada (cura = 0)
   └─ VIVO: Apenas curar
```

---

## 📐 Validações de Criação

### Regras de Nome
- ✅ Não pode ser nulo
- ✅ Não pode ser vazio
- ✅ Sem limite de caracteres

### Regras de Idade
- ✅ Mínimo: 0 anos
- ✅ Máximo: 122 anos
- ❌ Não pode ser negativo

### Regras de Altura
- ✅ Mínimo: 0 metros
- ✅ Máximo: 2.74 metros
- ❌ Não pode ser negativo

---

## 🏆 Progressão de Níveis

### Guerreiro - Tabela de Dano

| Nível | Dano | Incremento |
|-------|------|------------|
| 1 | 30 | - |
| 2 | 35 | +5 |
| 3 | 40 | +5 |
| 4 | 45 | +5 |
| 5 | 50 | +5 |
| 6 | 55 | +5 |
| 7 | 60 | +5 |
| 8 | 65 | +5 |
| 9 | 70 | +5 |
| 10+ | 70 | 0 (máximo) |

### Arqueiro - Tabela de Evolução

| Nível | Flechas | Dano | Mudanças |
|-------|---------|------|----------|
| 1 | 1 | 40 | - |
| 2 | 1 | 40 | - |
| 3 | **2** | 41 | +flecha, +dano |
| 4 | 2 | 41 | - |
| 5 | **3** | 42 | +flecha, +dano |
| 6 | 3 | 42 | - |
| 7 | 3 | 43 | +dano |
| 9 | 3 | 44 | +dano |
| 11 | 3 | 45 | +dano |
| ... | 3 | ... | +dano (ímpares) |
| 21+ | 3 | 50 | 0 (máximo) |

### Mago - Tabela de Cura

| Nível | Cura | Incremento |
|-------|------|------------|
| 1 | 20 | - |
| 2 | 25 | +5 |
| 3 | 30 | +5 |
| 4 | 35 | +5 |
| 5 | 40 | +5 |
| 6 | 45 | +5 |
| 7 | 50 | +5 |
| 8+ | 50 | 0 (máximo) |

---

## 🎲 Probabilidades (Arqueiro)

### Dano Esperado por Flecha

Com dano máximo de 40:
- **Mínimo:** 0
- **Máximo:** 40
- **Médio:** 20
- **Distribuição:** Uniforme

### Dano Total Esperado (3 flechas, dano 40)

| Cenário | Dano Total |
|---------|------------|
| Melhor caso | 120 (40+40+40) |
| Pior caso | 0 (0+0+0) |
| Caso médio | 60 (20+20+20) |

---

## 🔢 Fórmulas

### Cálculo de Dano (recebeDano)
```java
for (int i = val; i != 0; i--) {
    if (vida - i >= 0) {
        vida -= i;
        break;
    }
}
```

**Comportamento:** Reduz dano se necessário para não ultrapassar 0

**Exemplos:**
- Vida=50, Dano=30 → Vida=20
- Vida=50, Dano=60 → Vida=0 (aplica 50)
- Vida=0, Dano=30 → Vida=0 (nenhum efeito)

### Cálculo de Cura (recebeCura)
```java
for (int i = val; i != 0; i--) {
    if (vida + i <= 100) {
        vida += i;
        break;
    }
}
```

**Comportamento:** Reduz cura se necessário para não ultrapassar 100

**Exemplos:**
- Vida=50, Cura=30 → Vida=80
- Vida=90, Cura=30 → Vida=100 (aplica 10)
- Vida=100, Cura=30 → Vida=100 (nenhum efeito)

### Ordenação (compareTo)
```
Prioridade 1: idade (ASC)
Prioridade 2: altura (ASC)
Prioridade 3: vida (ASC)
```

**Exemplos:**
```
Ana (20 anos, 1.60m, 100% vida)
Bob (20 anos, 1.70m, 80% vida)
Carl (25 anos, 1.60m, 100% vida)

Ordenado:
1. Ana  (menor idade, menor altura)
2. Bob  (mesma idade, maior altura)
3. Carl (maior idade)
```

---

## 💾 Estrutura de Dados

### ArrayList<Heroi> arena

**Características:**
- Dinâmica (cresce conforme necessário)
- Indexada (acesso por posição)
- Heterogênea (pode conter Guerreiro, Arqueiro, Mago)

**Operações:**
```java
arena.add(heroi)           // Adiciona ao final
arena.get(index)           // Acessa por índice
arena.remove(index)        // Remove por índice
arena.size()               // Tamanho atual
Collections.sort(arena)    // Ordena usando compareTo()
```

### ArrayList<Heroi> historico

**Uso por classe:**
- **Guerreiro/Arqueiro:** Lista de heróis mortos
- **Mago:** Lista de heróis revividos

**Atualização:**
```java
this.historico.add(alvo);  // Adiciona ao histórico
```

---

## ⚠️ Erros Comuns

### Erro: "Nome deve ser preenchido"
**Causa:** Nome vazio (`""`)
**Solução:** Informar nome válido

### Erro: "Idade nao pode ser negativa"
**Causa:** Idade < 0
**Solução:** Informar idade entre 0 e 122

### Erro: "Altura máxima permitida é de 2.74m"
**Causa:** Altura > 2.74
**Solução:** Informar altura entre 0 e 2.74

### Erro: "O Herói informado é inexistente"
**Causa:** ID fora do intervalo [0, arena.size()-1]
**Solução:** Verificar IDs com opção 5 (Mostrar Heróis)

### Erro: NumberFormatException
**Causa:** Entrada não numérica onde número é esperado
**Solução:** Informar apenas números quando solicitado

---

## 🔧 Atalhos e Dicas

### Criar Equipe Balanceada
```
Opção 1: Guerreiro (Tank)
Opção 2: Arqueiro (DPS)
Opção 3: Mago (Suporte)
```

### Maximizar Evolução
1. Use Guerreiro para kills consistentes
2. Use Arqueiro contra alvos com pouca vida
3. Reviva com Mago sempre que possível

### Estratégia de Combate
```
Turno 1: Arqueiro ataca → Alta chance de kill
Turno 2: Guerreiro finaliza → Evolui
Turno 3: Mago cura guerreiro
```

### Ver Status Completo
```
Opção 5 → Mostra:
- ID do herói
- Nome
- Status (VIVO/ATORDOADO/MORTO)
- Nível
- Idade e Altura
- Estatísticas da classe
```

---

## 📱 Interface CLI

### Exemplo de Sessão
```
+----------------------------------------+
|     Bem vindo ao Clash of Realms       |
+----------------------------------------+
|      (0) Sair                          |
|      (1) Crie seu Guerreiro            |
|      (2) Crie seu Arqueiro             |
|      (3) Crie seu Mago                 |
|      (4) Retirar Herói                 |
|      (5) Mostrar Heróis                |
|      (6) Ordene os Heróis              |
|      (7) Aniversario de um Herói       |
|      (8) Usar habilidade de um Herói   |
+----------------------------------------+
> 1

Nome do Heroi :
> Thor
Idade do Heroi :
> 35
Altura do Heroi :
> 1.95

✓ Guerreiro criado com sucesso!
```

---

## 🎓 Conceitos de POO Aplicados

| Conceito | Implementação |
|----------|---------------|
| **Abstração** | Classe `Heroi` (abstract) |
| **Encapsulamento** | Atributos `private`, getters `public` |
| **Herança** | `Guerreiro extends Heroi` |
| **Polimorfismo** | `habilidade()` sobrescrito |
| **Interface** | `implements Comparable` |
| **Composição** | `ArrayList<Heroi> historico` |
| **Agregação** | `ArrayList<Heroi> arena` |

---

## 📚 Métodos Principais

### Heroi (Classe Base)

```java
// Getters
getNome(), getVida(), getIdade(), getAltura()
getNivel(), getMorto(), getRevivido(), getHistorico()

// Modificadores
upar(), aniversario(), morre(), revive()
recebeDano(int), recebeCura(int)

// Abstratos
habilidade(Heroi) // Implementado pelas subclasses
evoluir()         // Implementado pelas subclasses

// Sobrepostos
compareTo(Object) // Ordenação
toString()        // Representação textual
```

### App (Controle)

```java
main(String[] args) // Ponto de entrada
```

---

*Guia Rápido - Clash of Realms v1.0*
