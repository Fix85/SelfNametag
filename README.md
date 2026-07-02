# Self Nametag

[English](#english) | [Русский](#русский)

Fabric client mod that shows your own nametag above your head when in third-person view (F5).

## Branches

| Branch | Minecraft | Java | Mappings |
|---|---|---|---|
| `mc-1.21.1` | 1.21.1 | 21 | yarn |
| `mc-1.21.4` | 1.21.4 | 21 | yarn |
| `mc-1.21.8` | 1.21.8 | 21 | yarn |
| `mc-1.21.11` | 1.21.11 | 21 | yarn |
| `mc-26.1` | 26.1.x | 25 | Mojang |
| `mc-26.2` | 26.2 | 25 | Mojang |

## English

A small Fabric client mod: when you press F5 to switch to third-person, you can see your own nickname floating above your head, just like every other player on the server.

### Controls

- **`N`** — toggle the mod on/off (saved to `config/selfnametag.json`).

### Behaviour

- Active only on the client (server is untouched).
- Shows the nametag only for the local player and only when the camera is **not** in first-person.
- Uses your existing display name (with team prefixes/colors) if applicable.

## Русский

Клиентский Fabric-мод: когда нажимаешь F5 (третье лицо), над твоей головой виден твой собственный ник — как у всех остальных игроков на сервере.

### Управление

- **`N`** — включить / выключить мод (состояние сохраняется в `config/selfnametag.json`).

### Поведение

- Только клиент. Сервер не затрагивается.
- Никнейм показывается только для локального игрока и только если камера не в первом лице.
- Используется отображаемое имя игрока (с префиксами/цветами команды), если они есть.
